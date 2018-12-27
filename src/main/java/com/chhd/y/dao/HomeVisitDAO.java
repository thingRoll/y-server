package com.chhd.y.dao;

import com.chhd.y.dto.VisitChartDTO;
import com.chhd.y.pojo.HomeVisit;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * HomeVisitDAO继承基类
 */
@Repository
public interface HomeVisitDAO extends MyBatisBaseDao<HomeVisit, Long> {

    List<VisitChartDTO> selectByLastWeek(String pattern);

    List<VisitChartDTO> selectByLastYearHalf(String pattern);
}