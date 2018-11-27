package com.chhd.y.controller;

import com.chhd.y.common.Response;
import com.chhd.y.pojo.ArticleWithBLOBs;
import com.chhd.y.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/article/")
@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "add.do", method = RequestMethod.POST)
    @ResponseBody
    public Response add(ArticleWithBLOBs article) {
        return articleService.add(article);
    }

    @RequestMapping(value = "detail.do", method = RequestMethod.GET)
    @ResponseBody
    public Response detail(Long id) {
        return articleService.detail(id);
    }
}
