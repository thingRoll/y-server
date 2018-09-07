package com.chhd.y.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.chhd.y.common.Response;
import com.chhd.y.dao.UserDAO;
import com.chhd.y.dto.UserDto;
import com.chhd.y.pojo.User;
import com.chhd.y.service.UserService;
import com.chhd.y.util.MD5Utils;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public Response add(User user) {
        Response checkData = checkAddData(user);
        if (checkData != null) {
            return checkData;
        }
        user.setPassword(MD5Utils.encode(user.getPassword()));
        int row = userDAO.insert(user);
        if (row > 0) {
            return get(user.getId());
        } else {
            return Response.createByError();
        }
    }

    private Response checkAddData(User user) {
        if (StringUtils.isBlank(user.getUsername())) {
            return Response.createByInvalidArgument("name is null");
        } else if (StringUtils.isBlank(user.getPassword())) {
            return Response.createByInvalidArgument("password is null");
        } else if (user.getRole() == null) {
            return Response.createByInvalidArgument("role is null");
        } else if (user.getRole() == 0) {
            return Response.createByInvalidArgument("role is invalid");
        } else if (userDAO.selectByUsername(user.getUsername()) != null) {
            return Response.createByError("用户已存在");
        }
        return null;
    }

    @Override
    public Response update(User user) {
        Response checkData = checkUpdateData(user);
        if (checkData != null) {
            return checkData;
        }
        user.setUsername(null);
        user.setToken(null);
        user.setPassword(null);
        user.setCreateTime(null);
        user.setUpdateTime(null);
        int row = userDAO.updateByPrimaryKeySelective(user);
        if (row > 0) {
            return get(user.getId());
        } else {
            return Response.createByError();
        }
    }

    private Response checkUpdateData(User user) {
        if (user.getId() == null) {
            return Response.createByInvalidArgument("id is null");
        } else if (user.getRole() == 0) {
            return Response.createByInvalidArgument("role is invalid");
        }
        return null;
    }

    @Override
    public Response get(Long id) {
        User user = userDAO.selectByPrimaryKey(id);
        UserDto userDto = toUserDto(user);
        userDto.setToken(null);
        return Response.createBySuccess(userDto);
    }

    @Override
    public Response login(String username, String password) {
        Response checkData = checkLoginData(username, password);
        if (checkData != null) {
            return checkData;
        }
        User user = userDAO.selectByUsername(username);
        if (user == null) {
            return Response.createByError("用户不存在");
        } else if (MD5Utils.encode(password).equals(user.getPassword())) {
            return Response.createByError("密码错误");
        }
        user.setToken(createToken(user));
        userDAO.updateByPrimaryKeySelective(user);
        return Response.createBySuccess(toUserDto(user));
    }

    private Response checkLoginData(String name, String password) {
        if (StringUtils.isBlank(name)) {
            return Response.createByInvalidArgument("name is null");
        } else if (StringUtils.isBlank(password)) {
            return Response.createByInvalidArgument("password is null");
        }
        return null;
    }

    private String createToken(User user) {
        String token = "";
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            Map<String, Object> map = Maps.newHashMap();
            map.put("alg", "HS256");
            map.put("typ", "JWT");
            token = JWT.create().withHeader(map)
                    .withClaim("id", user.getId())
                    .withClaim("username", user.getUsername())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                    .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }

    @Override
    public Response resetPassword(Long id, String password) {
        User user = userDAO.selectByPrimaryKey(id);
        user.setPassword(MD5Utils.encode(password));
        userDAO.updateByPrimaryKeySelective(user);
        UserDto userDto = toUserDto(user);
        userDto.setToken(null);
        return Response.createBySuccess(userDto);
    }

    private UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }


}
