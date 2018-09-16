package com.chhd.y.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.chhd.y.common.Response;
import com.chhd.y.dao.UserDAO;
import com.chhd.y.dto.UserDTO;
import com.chhd.y.pojo.User;
import com.chhd.y.service.UserService;
import com.chhd.y.util.MD5Utils;
import com.chhd.y.util.MailUtils;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

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
        user.setRole(1);
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
        } else if (userDAO.selectByUsername(user.getUsername()) != null) {
            return Response.createByError("用户已存在");
        }
        return null;
    }

    @Override
    public Response modify(User user) {
        Response checkData = checkModifyData(user);
        if (checkData != null) {
            return checkData;
        }
        user.setAccount(null);
        user.setUsername(null);
        user.setToken(null);
        user.setPassword(null);
        user.setEmailCode(null);
        user.setEmailState(null);
        user.setTelCode(null);
        user.setTelState(null);
        user.setCreateTime(null);
        user.setUpdateTime(null);
        int row = userDAO.updateByPrimaryKeySelective(user);
        if (row > 0) {
            return get(user.getId());
        } else {
            return Response.createByError();
        }
    }

    private Response checkModifyData(User user) {
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
        UserDTO userDto = toUserDto(user);
        userDto.setToken(null);
        return Response.createBySuccess(userDto);
    }

    @Override
    public Response login(String account, String password, Integer os, String device) {
        Response checkData = checkLoginData(account, password);
        if (checkData != null) {
            return checkData;
        }
        User user;
        if (account.matches(Regex.email.getS())) {
            user = userDAO.selectByEmail(account);
            if (user == null) {
                user = userDAO.selectByUsername(account);
            } else if (user.getEmailState() == 0) {
                return Response.createByError("邮箱未激活");
            }
        } else {
            user = userDAO.selectByUsername(account);
        }
        if (user == null) {
            return Response.createByError("用户不存在");
        } else if (!MD5Utils.encode(password).equals(user.getPassword())) {
            return Response.createByError("密码错误");
        }
        if (StringUtils.isBlank(user.getTokenUid())) {
            user.setTokenUid(UUID.randomUUID().toString());
        }
        user.setAccount(account);
        user.setToken(createToken(user));
        userDAO.updateByPrimaryKeySelective(user);
        return Response.createBySuccess(toUserDto(user));
    }

    private Response checkLoginData(String account, String password) {
        if (StringUtils.isBlank(account)) {
            return Response.createByInvalidArgument("account is null");
        } else if (StringUtils.isBlank(password)) {
            return Response.createByInvalidArgument("password is null");
        }
        return null;
    }

    @Override
    public Response modifyPassword(Long id, String password, String newPassword) {
        Response checkData = checkModifyPwdData(id, password, newPassword);
        if (checkData != null) {
            return checkData;
        }
        User user = userDAO.selectByPrimaryKey(id);
        user.setPassword(MD5Utils.encode(newPassword));
        userDAO.updateByPrimaryKeySelective(user);
        return Response.createBySuccess();
    }

    @Override
    public Response logout(Long id) {
        User user = userDAO.selectByPrimaryKey(id);
        user.setTokenUid(UUID.randomUUID().toString());
        userDAO.updateByPrimaryKeySelective(user);
        return Response.createBySuccess();
    }

    @Override
    public Response sendEmailCode(Long id, String email) {
        if (StringUtils.isBlank(email)) {
            return Response.createByInvalidArgument("email is null");
        } else if (!email.matches(Regex.email.getS())) {
            return Response.createByInvalidArgument("邮箱无效");
        }
        /*
        2.随机产生规定范围内数字Eg：[1000,9999)
        num=(int)(Math.random()*8999)+1000;
        4.随机产生规定范围内数字Eg：（1000,9999]
        num=(int)(Math.random()*8999)+1000+1;
        5.随机产生规定范围内数字(1000,9999)
        num=(int)(Math.random()*8998)+1000+1;
        */
        int code = (int) (Math.random() * 8999) + 1000;
        boolean b = new MailUtils(email, "" + code).send();
        if (b) {
            User user = userDAO.selectByPrimaryKey(id);
            user.setEmail(email);
            user.setEmailCode(code);
            userDAO.updateByPrimaryKeySelective(user);
            return Response.createBySuccess();
        } else {
            return Response.createByError();
        }
    }

    @Override
    public Response bindEmail(Long id, Integer code) {
        if (code == null) {
            return Response.createByInvalidArgument("code is null");
        }
        User user = userDAO.selectByPrimaryKey(id);
        if (!code.equals(user.getEmailCode())) {
            return Response.createByError("验证码错误");
        }
        user.setEmailState(1);
        userDAO.updateByPrimaryKeySelective(user);
        return Response.createBySuccess();
    }

    @Override
    public Response unbindEmail(Long id) {
        User user = userDAO.selectByPrimaryKey(id);
        user.setEmail(null);
        user.setEmailCode(null);
        user.setEmailCode(0);
        int row = userDAO.updateByPrimaryKey(user);
        if (row > 0) {
            return Response.createBySuccess();
        } else {
            return Response.createByError();
        }
    }

    private Response checkModifyPwdData(Long id, String password, String newPassword) {
        if (StringUtils.isBlank(password)) {
            return Response.createByInvalidArgument("password is null");
        } else if (StringUtils.isBlank(newPassword)) {
            return Response.createByInvalidArgument("newPassword is null");
        }
        User user = userDAO.selectByPrimaryKey(id);
        if (!MD5Utils.encode(password).equals(user.getPassword())) {
            return Response.createByError("密码错误");
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
                    .withClaim("tokenUid", user.getTokenUid())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                    .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }

    private UserDTO toUserDto(User user) {
        UserDTO userDto = new UserDTO();
        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }
}
