package com.chhd.y.service.impl;

import com.chhd.y.common.Response;
import com.chhd.y.dao.ArticleCategoryDAO;
import com.chhd.y.dao.ArticleDAO;
import com.chhd.y.dao.ArticleVisitDAO;
import com.chhd.y.dao.UserDAO;
import com.chhd.y.dto.ArticleDTO;
import com.chhd.y.dto.ArticleVisitDTO;
import com.chhd.y.pojo.ArticleCategory;
import com.chhd.y.pojo.ArticleVisit;
import com.chhd.y.pojo.ArticleWithBLOBs;
import com.chhd.y.pojo.User;
import com.chhd.y.service.HomeService;
import com.chhd.y.util.JsonUtils;
import com.chhd.y.util.PropertiesUtils;
import com.chhd.y.util.RoleUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        List<ArticleCategory> categoryList =
                articleCategoryDAO.selectArticleCategoryByParentIdPlus(-1L, RoleUtils.checkPlus(getUser()));
        for (ArticleCategory category : categoryList) {
            Map<Object, Object> group = new HashMap<>();
            group.put("title", category.getName());
            User user = getUser();
            PageHelper.startPage(1, size);
            List<ArticleWithBLOBs> articleList =
                    articleDao.selectAllByPlusCategoryId(RoleUtils.checkPlus(user), category.getId());
            group.put("articleList", articleList);
            groupList.add(group);
        }
        return Response.createBySuccess(groupList);
    }
}
