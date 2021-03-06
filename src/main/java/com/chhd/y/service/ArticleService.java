package com.chhd.y.service;

import com.chhd.y.common.Response;
import com.chhd.y.pojo.ArticleWithBLOBs;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

public interface ArticleService {

    Response add(ArticleWithBLOBs article);

    Response detail(Long id);

    Response list(Long userId, int pageNum, int pageSize, Long categoryId, Map<String, String> map);

    Response disable(long id, int disable);

    Response visit(Long userId, long articleId, int os, String device);
}
