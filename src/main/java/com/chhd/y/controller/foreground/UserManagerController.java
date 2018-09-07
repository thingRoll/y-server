package com.chhd.y.controller.foreground;

import com.chhd.y.common.Response;
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

@RequestMapping("/manage/user/")
@Controller
public class UserManagerController {

    Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired
    protected HttpServletRequest request;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "reset_password.do", method = RequestMethod.POST)
    @ResponseBody
    public Response resetPassword(String password) {
        String token = request.getHeader("token");
        return userService.resetPassword(JwtUtils.getLong(token, "id"), password);
    }
}
