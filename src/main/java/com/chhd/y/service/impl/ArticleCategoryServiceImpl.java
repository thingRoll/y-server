package com.chhd.y.service.impl;

import com.chhd.y.common.Response;
import com.chhd.y.dao.ArticleCategoryDAO;
import com.chhd.y.dao.UserDAO;
import com.chhd.y.dto.ArticleCategoryDTO;
import com.chhd.y.pojo.ArticleCategory;
import com.chhd.y.pojo.User;
import com.chhd.y.service.ArticleCategoryService;
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

    @Override
    public Response list(Long userId, Long parentId) {
        User user = userDAO.selectByPrimaryKey(userId);
        int plus;
        if (user == null || user.getRole() == 1) {
            plus = 0;
        } else {
            plus = 1;
        }
        List<ArticleCategory> articleCategoryList = categoryDAO.selectArticleCategoryByParentIdPlus(parentId, plus);
        if (articleCategoryList != null) {
            return Response.createBySuccess(createArticleCategoryDTOList(parentId, plus));
        } else {
            return Response.createByError();
        }
    }

    private List<ArticleCategoryDTO> createArticleCategoryDTOList(
            Long parentId,
            int plus) {
        List<ArticleCategoryDTO> dtoList = Lists.newArrayList();
        List<ArticleCategory> articleCategoryList = categoryDAO.selectArticleCategoryByParentIdPlus(parentId, plus);
        if (articleCategoryList.isEmpty()) {
            return dtoList;
        }
        for (ArticleCategory articleCategory : articleCategoryList) {
            ArticleCategoryDTO dto = new ArticleCategoryDTO();
            BeanUtils.copyProperties(articleCategory, dto);
            dto.setIcon(null);
            List<ArticleCategoryDTO> childList = createArticleCategoryDTOList(articleCategory.getId(), plus);
            if (!childList.isEmpty()) {
                dto.setChildList(childList);
            }
            dtoList.add(dto);
        }
        Collections.sort(dtoList, new DefaultSort());
        return dtoList;
    }

    private class DefaultSort implements Comparator<ArticleCategoryDTO> {

        @Override
        public int compare(ArticleCategoryDTO o1, ArticleCategoryDTO o2) {
            int sortDiff = o1.getSort() - o2.getSort();
            int timeDiff = (int) (o1.getCreateTime().getTime() - o2.getCreateTime().getTime());
            if (sortDiff != 0) {
                return sortDiff;
            } else {
                return timeDiff;
            }
        }
    }

    @Override
    public Response disable(Long categoryId, int disable) {
        ArticleCategory category = categoryDAO.selectByPrimaryKey(categoryId);
        if (category == null) {
            return Response.createByError("找不到文章分类");
        }
        category.setDisable(disable);
        if (disable==1){
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
