package com.huike.travel.service;

import com.huike.travel.domain.RouteImg;

import java.util.List;

public interface RouteImgService {

    List<RouteImg> findRouteImgByRid(int rid);
}
