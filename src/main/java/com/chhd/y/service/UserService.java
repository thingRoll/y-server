package com.chhd.y.service;

import com.chhd.y.common.Response;
import com.chhd.y.pojo.User;

public interface UserService {

    Response add(User user);

    Response update(User user);

    Response get(Long id);

    Response login(String name, String password);

    Response resetPassword(Long id, String password);
}
