package com.chhd.y.controller;

import com.chhd.y.common.Response;
import com.chhd.y.service.HomeService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RequestMapping("/home/")
@Controller
public class HomeController extends BaseController {

    @Autowired
    private HomeService homeService;

    @RequestMapping(value = "banner.do", method = RequestMethod.GET)
    @ResponseBody
    public Response banner(@RequestParam(defaultValue = "7") int size) {
        return homeService.banner(size);
    }

    @RequestMapping(value = "latest.do", method = RequestMethod.GET)
    @ResponseBody
    public Response latest(
            @RequestParam(defaultValue = "7") int size) {
        return homeService.latest(getUserId(), size);
    }

    @RequestMapping(value = "hottest.do", method = RequestMethod.GET)
    @ResponseBody
    public Response hottest(
            @RequestParam(defaultValue = "7") int size) {
        return homeService.hottest(getUserId(), size);
    }

    @RequestMapping(value = "group_list.do", method = RequestMethod.GET)
    @ResponseBody
    public Response groupList(@RequestParam(defaultValue = "12") int size) {
        return homeService.groupList(size);
    }

    @RequestMapping(value = "visit.do", method = RequestMethod.GET)
    @ResponseBody
    public Response visit(HttpSession session) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("sessionId", session.getId());
        if (getUserId() != null) {
            map.put("userId", getUserId());
            map.put("username", getUser().getUsername());
        }
        map.put("os", getOs());
        map.put("device", getDevice());
        return homeService.visit(map);
    }
}
