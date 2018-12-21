package com.chhd.y.controller;

import com.chhd.y.dao.UserDAO;
import com.chhd.y.pojo.User;
import com.chhd.y.util.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public class BaseController {

    @Autowired
    private UserDAO userDAO;

    protected Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired
    protected HttpServletRequest request;

    protected Integer getOs() {
        return Integer.parseInt(request.getHeader("os"));
    }

    protected String getDevice() {
        return request.getHeader("device");
    }

    protected String getToken() {
        return request.getHeader("token");
    }

    protected Long getUserId() {
        try {
            return JwtUtils.getLong(getToken(), "id");
        } catch (Exception ignored) {

        }
        return null;
    }

    public User getUser() {
        User user = null;
        if (getUserId() != null) {
            user = userDAO.selectByPrimaryKey(getUserId());
        }
        return user;
    }
}
