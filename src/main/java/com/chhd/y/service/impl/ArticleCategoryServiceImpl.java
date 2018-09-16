package com.chhd.y.service.impl;

import com.chhd.y.common.Response;
import com.chhd.y.dao.ArticleCategoryDAO;
import com.chhd.y.dao.UserDAO;
import com.chhd.y.dto.ArticleCategoryDTO;
import com.chhd.y.pojo.ArticleCategory;
import com.chhd.y.pojo.User;
import com.chhd.y.service.ArticleCategoryService;
import com.chhd.y.service.UserService;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("ArticleCategoryService")
public class ArticleCategoryServiceImpl implements ArticleCategoryService {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private ArticleCategoryDAO categoryDAO;

    @Override
    public Response list(Long userId) {
        User user = userDAO.selectByPrimaryKey(userId);
        int plus;
        if (user == null || user.getRole() == 1) {
            plus = 0;
        } else {
            plus = 1;
        }
        List<ArticleCategory> articleCategoryList = categoryDAO.selectArticleCategoryByPlus(plus);
        if (articleCategoryList != null) {
            List<ArticleCategoryDTO> dtoList = Lists.newArrayList();
            for (ArticleCategory articleCategory : articleCategoryList) {
                if (articleCategory.getParentId() == 0) {
                    ArticleCategoryDTO dto = new ArticleCategoryDTO();
                    BeanUtils.copyProperties(articleCategory, dto);
                    dtoList.add(dto);
                }
            }
            Collections.sort(dtoList, new DefaultSort());
            for (ArticleCategory articleCategory : articleCategoryList) {
                if (articleCategory.getParentId() != 0) {
                    ArticleCategoryDTO parent = getParentCategory(dtoList, articleCategory.getParentId());
                    if (parent != null) {
                        ArticleCategoryDTO dto = new ArticleCategoryDTO();
                        BeanUtils.copyProperties(articleCategory, dto);
                        parent.getChildList().add(dto);
                    }
                }
            }
            for (ArticleCategoryDTO dto : dtoList) {
                Collections.sort(dto.getChildList(), new DefaultSort());
            }
            return Response.createBySuccess(dtoList);
        } else {
            return Response.createByError();
        }
    }

    private ArticleCategoryDTO getParentCategory(List<ArticleCategoryDTO> list, Long parentId) {
        for (ArticleCategoryDTO dto : list) {
            if (Objects.equals(dto.getId(), parentId)) {
                return dto;
            }
        }
        return null;
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
}
