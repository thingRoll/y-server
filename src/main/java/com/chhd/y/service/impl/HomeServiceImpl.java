package com.chhd.y.service.impl;

import com.chhd.y.common.Response;
import com.chhd.y.dao.*;
import com.chhd.y.dto.*;
import com.chhd.y.pojo.ArticleVisit;
import com.chhd.y.pojo.ArticleWithBLOBs;
import com.chhd.y.pojo.HomeVisit;
import com.chhd.y.pojo.User;
import com.chhd.y.service.ArticleCategoryService;
import com.chhd.y.service.ArticleService;
import com.chhd.y.service.HomeService;
import com.chhd.y.service.UserService;
import com.chhd.y.util.JsonUtils;
import com.chhd.y.util.PropertiesUtils;
import com.chhd.y.util.RoleUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service("HomeService")
public class HomeServiceImpl extends BaseService implements HomeService {

    private static String imgBaseUrlFlag = PropertiesUtils.getProperty("img.flag");
    private static String imgBaseUrl = PropertiesUtils.getProperty("ftp.http.prefix");

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private ArticleDAO articleDao;
    @Autowired
    private ArticleVisitDAO articleVisitDAO;
    @Autowired
    private ArticleCategoryDAO articleCategoryDAO;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private UserService userService;
    @Autowired
    private HomeVisitDAO homeVisitDAO;
    @Autowired
    private ArticleCategoryService articleCategoryService;

    @Override
    public Response banner(int size) {
        User user = userDAO.selectByPrimaryKey(getUserId());
        PageHelper.startPage(1, size);
        List<ArticleWithBLOBs> articleList = articleDao.selectAllByPlus(RoleUtils.checkPlus(user));
        List<Map> articleDTOList = Lists.newArrayList();
        for (ArticleWithBLOBs item : articleList) {
            Map<Object, Object> dto = new HashMap<>();
            dto.put("cover", item.getCover().replace(imgBaseUrlFlag, imgBaseUrl));
            dto.put("title", item.getTitle());
            dto.put("id", item.getId());
            articleDTOList.add(dto);
        }
        return Response.createBySuccess(articleDTOList);
    }

    @Override
    public Response latest(Long userId, int size) {
        User user = userDAO.selectByPrimaryKey(userId);
        PageHelper.startPage(1, size);
        List<ArticleWithBLOBs> articleList = articleDao.selectAllByPlus(RoleUtils.checkPlus(user));
        List<ArticleDTO> articleDTOList = Lists.newArrayList();
        for (ArticleWithBLOBs item : articleList) {
            ArticleDTO articleDTO = new ArticleDTO();
            BeanUtils.copyProperties(item, articleDTO);
            articleDTO.setCover(articleDTO.getCover().replace(imgBaseUrlFlag, imgBaseUrl));
            articleDTO.setContent(articleDTO.getContent().replace(imgBaseUrlFlag, imgBaseUrl));
            articleDTOList.add(articleDTO);
        }
        PageInfo<ArticleDTO> pageInfo = new PageInfo<>(articleDTOList);
        return Response.createBySuccess(pageInfo.getList());
    }

    public Response hottest(Long userId, int size) {
        User user = userDAO.selectByPrimaryKey(userId);
        PageHelper.startPage(1, size);
        List<ArticleVisit> articleVisitList = articleVisitDAO.selectByLast7Days(RoleUtils.checkPlus(user));
        List<ArticleVisitDTO> articleVisitDTOList = JsonUtils.copyList(articleVisitList, ArticleVisitDTO.class);
        return Response.createBySuccess(articleVisitDTOList);
    }

    @Override
    public Response groupList(int size) {
        List<Object> groupList = Lists.newArrayList();
        Response response = articleCategoryService.list(getUserId(), -1L, 0);
        List<ArticleCategoryDTO> categoryList = JsonUtils.copyList(response.getData(), ArticleCategoryDTO.class);
        for (ArticleCategoryDTO category : categoryList) {
            if (category.getPlus() == 0 && category.getChildList() == null) {
                Map<Object, Object> group = new LinkedHashMap<>();
                group.put("title", category.getName());
                User user = getUser();
                PageHelper.startPage(1, size);
                List<ArticleWithBLOBs> articleList =
                        articleDao.selectAllByPlusCategoryId(RoleUtils.checkPlus(user), category.getId());
                for (ArticleWithBLOBs item : articleList) {
                    item.setCover(item.getCover().replace(imgBaseUrlFlag, imgBaseUrl));
                    item.setContent(item.getContent().replace(imgBaseUrlFlag, imgBaseUrl));
                }
                List<ArticleDTO> articleDTOList = JsonUtils.copyList(articleList, ArticleDTO.class);
                group.put("articleList", articleDTOList);
                groupList.add(group);
            }
        }
        return Response.createBySuccess(groupList);
    }

    @Override
    public Response visit(Map map) {
        HomeVisit record = new HomeVisit();
        record.setSessionId(map.get("sessionId") + "");
        if (map.get("userId") != null) {
            record.setUserId(Long.parseLong(map.get("userId") + ""));
            record.setUsername(map.get("username") + "");
        }
        if (map.get("os") != null) {
            record.setOs(Integer.parseInt(map.get("os") + ""));
        }
        if (map.get("device") != null) {
            record.setDevice(map.get("device") + "");
        }
        homeVisitDAO.insert(record);
        return Response.createBySuccess(record.getSessionId());
    }

    @Override
    public Response count() {
        Map<Object, Object> map = new LinkedHashMap<>();
        Response response = articleService.list(getUserId(), 1, 0, null,
                Maps.<String, String>newHashMap());
        PageInfoDTO pageInfoDTO = (PageInfoDTO) response.getData();
        map.put("articleCount", pageInfoDTO.getTotal());
        response = userService.list(getUserId(), 1, 0);
        pageInfoDTO = (PageInfoDTO) response.getData();
        map.put("userCount", pageInfoDTO.getTotal());
        return Response.createBySuccess(map);
    }

    @Override
    public Response visitChart(String duration) {
        List<VisitChartDTO> visitChartDTOList = new ArrayList<>();
        if ("week".equals(duration)) {
            visitChartDTOList = homeVisitDAO.selectByLastWeek();
        } else if ("yearHalf".equals(duration)) {
            visitChartDTOList = homeVisitDAO.selectByLastYearHalf();
        }
        return Response.createBySuccess(visitChartDTOList);
    }
}
