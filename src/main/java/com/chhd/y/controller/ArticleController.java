package com.chhd.y.controller;

import com.chhd.y.common.Response;
import com.chhd.y.pojo.ArticleWithBLOBs;
import com.chhd.y.service.ArticleService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(tags = "文章模块")
@RequestMapping("/article/")
@Controller
public class ArticleController extends BaseController {

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

    @ApiOperation(value = "文章列表")
    @RequestMapping(value = "list.do", method = RequestMethod.GET)
    @ResponseBody
    public Response list(@RequestParam(value = "pageNum", defaultValue = "1")
                         @ApiParam(value = "页码") int pageNum,
                         @RequestParam(value = "pageSize", defaultValue = "10")
                         @ApiParam(value = "分页") int pageSize) {
        return articleService.list(getUserId(), pageNum, pageSize);
    }

    @RequestMapping(value = "disable.do", method = RequestMethod.POST)
    @ResponseBody
    public Response disable(long id, int disable) {
        return articleService.disable(id, disable);
    }
}
