package com.chhd.y.service;

import com.chhd.y.common.Constant;
import com.chhd.y.common.Response;
import com.chhd.y.pojo.User;

public interface UserService extends Constant {

    Response add(User user);

    Response modify(User user);

    Response get(Long id);

    Response login(String account, String password, Integer os, String device);

    Response modifyPassword(Long id, String password, String newPassword);

    Response logout(Long id);

    Response sendEmailCode(Long id, String email);

    Response bindEmail(Long id, Integer code);

    Response unbindEmail(Long id);
}
