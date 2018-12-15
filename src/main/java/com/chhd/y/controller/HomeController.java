package com.chhd.y.controller;

import com.chhd.y.common.Response;
import com.chhd.y.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/home/")
@Controller
public class HomeController extends BaseController {

    @Autowired
    private HomeService homeService;

    @RequestMapping(value = "banner.do", method = RequestMethod.GET)
    @ResponseBody
    public Response banner() {
        return null;
    }

    @RequestMapping(value = "latest.do", method = RequestMethod.GET)
    @ResponseBody
    public Response latest() {
        return homeService.latest(getUserId());
    }

    @RequestMapping(value = "hottest.do", method = RequestMethod.GET)
    @ResponseBody
    public Response hottest() {
        return homeService.hottest(getUserId());
    }
}
