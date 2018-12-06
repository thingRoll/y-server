package com.chhd.y.dao;

import com.chhd.y.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserDAO继承基类
 */
@Repository
public interface UserDAO extends MyBatisBaseDao<User, Long> {

    User selectByLogin(@Param("username") String username, @Param("password") String password);

    User selectByToken(String token);

    User selectByUsername(String username);

    User selectByEmail(String username);

    List<User> selectAll();
}