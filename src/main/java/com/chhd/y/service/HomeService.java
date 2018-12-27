package com.chhd.y.service;

import com.chhd.y.common.Response;

import java.util.Map;

public interface HomeService {

    Response banner(int size);

    Response latest(Long userId, int size);

    Response hottest(Long userId, int size);

    Response groupList(int size);

    Response visit(Map map);

    Response count();

    Response visitChart(String duration, String pattern);
}
