package com.chhd.y.service;

import com.chhd.y.common.Response;

public interface HomeService {

    Response banner();

    Response latest(Long userId);

    Response hottest(Long userId);
}
