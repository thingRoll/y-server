package com.chhd.y.controller.foreground;

import com.chhd.y.common.Response;
import com.chhd.y.controller.BaseController;
import com.chhd.y.service.ArticleCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/article/category/")
@Controller
public class ArticleCategoryController extends BaseController {

    @Autowired
    private ArticleCategoryService articleCategoryService;

    @RequestMapping(value = "list.do", method = RequestMethod.GET)
    @ResponseBody
    public Response list() {
        return articleCategoryService.list(getUserId());
    }
}
