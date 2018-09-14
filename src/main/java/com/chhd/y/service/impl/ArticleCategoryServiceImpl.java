package com.chhd.y.service.impl;

import com.chhd.y.common.Response;
import com.chhd.y.dao.ArticleCategoryDAO;
import com.chhd.y.pojo.ArticleCategory;
import com.chhd.y.service.ArticleCategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ArticleCategoryService")
public class ArticleCategoryServiceImpl implements ArticleCategoryService {

    @Autowired
    private ArticleCategoryDAO categoryDAO;

    @Override
    public Response add(ArticleCategory category) {
        Response checkData = checkAddData(category);
        if (checkData != null) {
            return checkData;
        }
        int row = categoryDAO.insert(category);
        if (row > 0) {
            return Response.createBySuccess();
        } else {
            return Response.createByError();
        }
    }

    private Response checkAddData(ArticleCategory category) {
        if (StringUtils.isBlank(category.getName())) {
            return Response.createByInvalidArgument("name is null");
        }
        return null;
    }
}
