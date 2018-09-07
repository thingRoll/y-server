package com.chhd.y.controller.background;

import com.chhd.y.common.Response;
import com.chhd.y.pojo.User;
import com.chhd.y.service.UserService;
import com.chhd.y.util.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/user/")
@Controller
public class UserController {

    Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired
    protected HttpServletRequest request;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "add.do", method = RequestMethod.POST)
    @ResponseBody
    public Response add(User user) {
        return userService.add(user);
    }

    @RequestMapping(value = "update.do", method = RequestMethod.POST)
    @ResponseBody
    public Response update(User user) {
        String token = request.getHeader("token");
        user.setId(JwtUtils.getLong(token, "id"));
        return userService.update(user);
    }

    @RequestMapping(value = "get.do", method = RequestMethod.GET)
    @ResponseBody
    public Response get() {
        String token = request.getHeader("token");
        return userService.get(JwtUtils.getLong(token, "id"));
    }

    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody
    public Response login(String username, String password) {
        return userService.login(username, password);
    }
}
