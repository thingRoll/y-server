package com.chhd.y.service.impl;

import com.chhd.y.common.Response;
import com.chhd.y.dao.ArticleDAO;
import com.chhd.y.dao.ArticleVisitDAO;
import com.chhd.y.dao.UserDAO;
import com.chhd.y.dto.ArticleDTO;
import com.chhd.y.dto.ArticleVisitDTO;
import com.chhd.y.pojo.ArticleVisit;
import com.chhd.y.pojo.ArticleWithBLOBs;
import com.chhd.y.pojo.User;
import com.chhd.y.service.HomeService;
import com.chhd.y.util.JsonUtils;
import com.chhd.y.util.RoleUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("HomeService")
public class HomeServiceImpl implements HomeService {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private ArticleDAO articleDao;
    @Autowired
    private ArticleVisitDAO articleVisitDAO;

    @Override
    public Response banner() {
        return null;
    }

    @Override
    public Response latest(Long userId) {
        User user = userDAO.selectByPrimaryKey(userId);
        PageHelper.startPage(1, 7);
        List<ArticleWithBLOBs> articleList = articleDao.selectAllByPlus(RoleUtils.checkPlus(user));
        List<ArticleDTO> articleDTOList = Lists.newArrayList();
        for (ArticleWithBLOBs item : articleList) {
            ArticleDTO articleDTO = new ArticleDTO();
            BeanUtils.copyProperties(item, articleDTO);
            articleDTOList.add(articleDTO);
        }
        PageInfo<ArticleDTO> pageInfo = new PageInfo<>(articleDTOList);
        return Response.createBySuccess(pageInfo.getList());
    }

    public Response hottest(Long userId) {
        User user = userDAO.selectByPrimaryKey(userId);
        List<ArticleVisit> articleVisitList = articleVisitDAO.selectByLast7Days(RoleUtils.checkPlus(user));
        List<ArticleVisitDTO> articleVisitDTOList = JsonUtils.copyList(articleVisitList, ArticleVisitDTO.class);
        return Response.createBySuccess(articleVisitDTOList);
    }
}
