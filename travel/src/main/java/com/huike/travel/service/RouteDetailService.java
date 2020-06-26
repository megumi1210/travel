package com.huike.travel.service;

import com.huike.travel.domain.RouteDetailResult;

public interface RouteDetailService {

    RouteDetailResult  findDetailByRid(int rid);
}
