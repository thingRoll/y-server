package com.chhd.y.controller.background;

import com.chhd.y.common.Response;
import com.chhd.y.controller.BaseController;
import com.chhd.y.pojo.ArticleCategory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/manage/article/category/")
@Controller
public class ArticleCategoryManageController extends BaseController {

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Response add(ArticleCategory category) {

        return null;
    }
}
