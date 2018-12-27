package com.chhd.y.dao;

import com.chhd.y.pojo.HomeVisit;
import org.springframework.stereotype.Repository;

/**
 * HomeVisitDAO继承基类
 */
@Repository
public interface HomeVisitDAO extends MyBatisBaseDao<HomeVisit, Long> {
}