package com.chhd.y.controller;

import com.chhd.y.common.Response;
import com.chhd.y.pojo.User;
import com.chhd.y.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(tags = "用户模块")
@RequestMapping("/user/")
@Controller
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户新增")
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
        return userService.login(account, password, getOs(), getDevice());
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

    @RequestMapping(value = "send_email_code.do", method = RequestMethod.POST)
    @ResponseBody
    public Response sendEmailCode(String email) {
        return userService.sendEmailCode(getUserId(), email);
    }

    @RequestMapping(value = "bind_email.do", method = RequestMethod.POST)
    @ResponseBody
    public Response bindEmail(Integer code) {
        return userService.bindEmail(getUserId(), code);
    }

    @RequestMapping(value = "unbind_email.do", method = RequestMethod.POST)
    @ResponseBody
    public Response unbindEmail() {
        return userService.unbindEmail(getUserId());
    }

    @RequestMapping(value = "list.do", method = RequestMethod.GET)
    @ResponseBody
    public Response list(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                         @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return userService.list(pageNum, pageSize);
    }

    @RequestMapping(value = "disable.do", method = RequestMethod.POST)
    @ResponseBody
    public Response disable(Long userId, int disable) {
        return userService.disable(userId, disable);
    }
}
