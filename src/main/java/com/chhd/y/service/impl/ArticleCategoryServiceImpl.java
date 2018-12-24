package com.chhd.y.service.impl;

import com.chhd.y.common.ArticleCategorySort;
import com.chhd.y.common.Response;
import com.chhd.y.dao.ArticleCategoryDAO;
import com.chhd.y.dao.ArticleDAO;
import com.chhd.y.dao.UserDAO;
import com.chhd.y.dto.ArticleCategoryDTO;
import com.chhd.y.pojo.ArticleCategory;
import com.chhd.y.pojo.User;
import com.chhd.y.service.ArticleCategoryService;
import com.chhd.y.util.RoleUtils;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service("ArticleCategoryService")
public class ArticleCategoryServiceImpl implements ArticleCategoryService {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private ArticleCategoryDAO categoryDAO;
    @Autowired
    private ArticleDAO articleDAO;

    @Override
    public Response list(Long userId, Long parentId, int icon) {
        User user = userDAO.selectByPrimaryKey(userId);
        int plus = RoleUtils.checkPlus(user);
        List<ArticleCategory> articleCategoryList = categoryDAO.selectArticleCategoryByParentIdPlus(parentId, plus);
        if (articleCategoryList != null) {
            return Response.createBySuccess(createArticleCategoryDTOList(parentId, plus, icon));
        } else {
            return Response.createByError();
        }
    }

    private List<ArticleCategoryDTO> createArticleCategoryDTOList(
            Long parentId,
            int plus,
            int icon) {
        List<ArticleCategoryDTO> dtoList = Lists.newArrayList();
        List<ArticleCategory> articleCategoryList = categoryDAO.selectArticleCategoryByParentIdPlus(parentId, plus);
        if (articleCategoryList.isEmpty()) {
            return dtoList;
        }
        Collections.sort(articleCategoryList, new ArticleCategorySort());
        for (ArticleCategory articleCategory : articleCategoryList) {
            ArticleCategoryDTO dto = new ArticleCategoryDTO();
            BeanUtils.copyProperties(articleCategory, dto);
            if (icon == 0)
                dto.setIcon(null);
            dto.setNum(articleDAO.selectCount(articleCategory.getId()));
            List<ArticleCategoryDTO> childList = createArticleCategoryDTOList(articleCategory.getId(), plus, icon);
            if (!childList.isEmpty()) {
                dto.setChildList(childList);
            }
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public Response disable(Long categoryId, int disable) {
        ArticleCategory category = categoryDAO.selectByPrimaryKey(categoryId);
        if (category == null) {
            return Response.createByError("找不到文章分类");
        }
        category.setDisable(disable);
        if (disable == 1) {
            List<ArticleCategory> childList = categoryDAO.selectArticleCategoryByParentId(categoryId);
            for (ArticleCategory child : childList) {
                child.setDisable(disable);
                categoryDAO.updateByPrimaryKeySelective(child);
            }
        }
        int row = categoryDAO.updateByPrimaryKeySelective(category);
        if (row > 0) {
            return Response.createBySuccess();
        } else {
            return Response.createByError();
        }
    }
}
