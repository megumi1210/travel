package com.huike.travel.dao;

import com.huike.travel.domain.PageParam;
import com.huike.travel.domain.PriceParam;
import com.huike.travel.domain.Route;

import java.util.List;

public interface RouteDao {

    List<Route>  findRoutesByPage(Route route , PriceParam priceParam, PageParam pageParam);
    int  getTotal(Route route ,PriceParam priceParam ,PageParam pageParam);
    int  updateCount(int count ,int rid);
}
