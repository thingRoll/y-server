package com.chhd.y.controller.background;

import com.chhd.y.common.Response;
import com.chhd.y.pojo.ArticleWithBLOBs;
import com.chhd.y.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/manage/article/")
@Controller
public class ArticleManageController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "add.do", method = RequestMethod.POST)
    @ResponseBody
    public Response add(ArticleWithBLOBs article) {
        return articleService.add(article);
    }
}
