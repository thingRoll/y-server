package com.chhd.y.service;

import com.chhd.y.common.Response;
import com.chhd.y.pojo.ArticleWithBLOBs;

public interface ArticleService {

    Response add(ArticleWithBLOBs article);

    Response detail(Long id);
}
