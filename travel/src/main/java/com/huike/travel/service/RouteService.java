package com.huike.travel.service;

import com.huike.travel.domain.PageInfo;
import com.huike.travel.domain.PageParam;
import com.huike.travel.domain.PriceParam;
import com.huike.travel.domain.Route;

public interface RouteService {

    Route  findRouteByRid(int rid);

    PageInfo<Route>  findRoutByPage(Route route , PriceParam priceParam , PageParam pageParam);

    boolean   updateCount(int count ,int rid);
}
