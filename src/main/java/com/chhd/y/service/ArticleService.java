package com.chhd.y.service;

import com.chhd.y.common.Response;
import com.chhd.y.pojo.ArticleWithBLOBs;
import org.springframework.web.bind.annotation.RequestParam;

public interface ArticleService {

    Response add(ArticleWithBLOBs article);

    Response detail(Long id);

    Response list(int pageNum, int pageSize);

    Response disable(long id, int disable);
}
