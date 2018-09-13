package com.chhd.y.controller;

import com.chhd.y.util.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public class BaseController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired
    protected HttpServletRequest request;

    protected String getToken() {
        return request.getHeader("token");
    }

    protected Long getUserId() {
        return JwtUtils.getLong(getToken(), "id");
    }
}
