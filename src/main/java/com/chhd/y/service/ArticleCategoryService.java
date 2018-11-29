package com.chhd.y.service;

import com.chhd.y.common.Response;
import com.chhd.y.pojo.ArticleCategory;

public interface ArticleCategoryService {

    Response list(Long userId, Long parentId);

    Response disable(Long categoryId, int disable);
}
