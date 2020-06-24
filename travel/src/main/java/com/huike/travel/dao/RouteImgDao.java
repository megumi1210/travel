package com.huike.travel.dao;

import com.huike.travel.domain.RouteImg;

import java.util.List;

public interface RouteImgDao {

    List<RouteImg> getRouteImgByRid(int rid);
}
