package com.chhd.y.dao;

import com.chhd.y.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * UserDAO继承基类
 */
@Repository
public interface UserDAO extends MyBatisBaseDao<User, Long> {

    User selectByLogin(@Param("name") String name, @Param("password") String password);

    User selectByToken(String token);

    User selectByUsername(String username);
}