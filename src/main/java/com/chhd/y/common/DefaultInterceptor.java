package com.chhd.y.common;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.chhd.y.dao.UserDAO;
import com.chhd.y.util.JsonUtils;
import com.chhd.y.util.JwtUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class DefaultInterceptor implements HandlerInterceptor {

    Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired
    private UserDAO userDAO;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object o) throws Exception {
        return checkHeader(request, response);
    }

    private boolean checkHeader(HttpServletRequest request, HttpServletResponse response) {
        String uri = request.getRequestURI();
        String os = request.getHeader("os");
        String token = request.getHeader("token");
        if (StringUtils.isBlank(os)) {
            writeResponse(response, JsonUtils.toJson(Response.createByInvalidHeader()));
            return false;
        }
        if (!checkTokenWithoutUri(uri)) {
            if (StringUtils.isBlank(token)) {
                writeResponse(response, JsonUtils.toJson(Response.createByInvalidHeader()));
                return false;
            }
            DecodedJWT jwt = JwtUtils.verifyJwt(token);
            if (jwt == null) {
                writeResponse(response, JsonUtils.toJson(Response.createByInvalidToken()));
                return false;
            } else {
                String tokenUid = userDAO.selectByPrimaryKey(JwtUtils.getLong(token, "id")).getTokenUid();
                if (!tokenUid.equals(JwtUtils.getString(token, "tokenUid"))) {
                    writeResponse(response, JsonUtils.toJson(Response.createByInvalidToken()));
                }
            }
        }
        return true;
    }

    private boolean checkTokenWithoutUri(String url) {
        return "/user/login.do".equals(url) ||
                "/user/add.do".equals(url);
    }

    private void writeResponse(HttpServletResponse response, String content) {
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(content);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null)
                writer.close();
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }
}
