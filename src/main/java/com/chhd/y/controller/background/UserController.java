package com.chhd.y.controller.background;

import com.chhd.y.common.Response;
import com.chhd.y.controller.BaseController;
import com.chhd.y.pojo.User;
import com.chhd.y.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/user/")
@Controller
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "add.do", method = RequestMethod.POST)
    @ResponseBody
    public Response add(User user) {
        return userService.add(user);
    }

    @RequestMapping(value = "modify.do", method = RequestMethod.POST)
    @ResponseBody
    public Response modify(User user) {
        user.setId(getUserId());
        return userService.modify(user);
    }

    @RequestMapping(value = "get.do", method = RequestMethod.GET)
    @ResponseBody
    public Response get() {
        return userService.get(getUserId());
    }

    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody
    public Response login(String account, String password) {
        return userService.login(account, password);
    }

    @RequestMapping(value = "modify_password.do", method = RequestMethod.POST)
    @ResponseBody
    public Response modifyPassword(String password, String newPassword) {
        return userService.modifyPassword(getUserId(), password, newPassword);
    }

    @RequestMapping(value = "logout.do", method = RequestMethod.GET)
    @ResponseBody
    public Response logout() {
        return userService.logout(getUserId());
    }

    @RequestMapping(value = "send_code_for_email.do", method = RequestMethod.POST)
    @ResponseBody
    public Response sendCodeForEmail(String email) {
        return userService.sendCodeForEmail(getUserId(), email);
    }

    @RequestMapping(value = "bind_email.do")
    @ResponseBody
    public Response bindEmail(Integer code) {
        return userService.bindEmail(getUserId(), code);
    }

    @RequestMapping(value = "unbind_email.do")
    @ResponseBody
    public Response unbindEmail() {
        return userService.unbindEmail(getUserId());
    }
}
