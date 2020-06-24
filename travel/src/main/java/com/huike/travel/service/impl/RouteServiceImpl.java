package com.huike.travel.service.impl;

import com.huike.travel.dao.RouteDao;
import com.huike.travel.dao.impl.RouteDaoImpl;
import com.huike.travel.domain.PageInfo;
import com.huike.travel.domain.PageParam;
import com.huike.travel.domain.PriceParam;
import com.huike.travel.domain.Route;
import com.huike.travel.service.RouteService;

import java.util.List;

public class RouteServiceImpl implements RouteService {

    private RouteDao routeDao = new RouteDaoImpl();

    @Override
    public PageInfo<Route> findRoutByPage(Route route, PriceParam priceParam, PageParam pageParam) {

        List<Route>  resultSet =  routeDao.findRoutesByPage(route,priceParam,pageParam);
        int total = routeDao.getTotal(route,priceParam,pageParam);
        return PageInfo.of(resultSet,pageParam,total);
    }
}
