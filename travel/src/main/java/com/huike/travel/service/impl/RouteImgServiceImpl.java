package com.huike.travel.service.impl;

import com.huike.travel.dao.RouteImgDao;
import com.huike.travel.dao.impl.RouteImgDaoImpl;
import com.huike.travel.domain.RouteImg;
import com.huike.travel.service.RouteImgService;

import java.util.ArrayList;
import java.util.List;

public class RouteImgServiceImpl implements RouteImgService {
    private RouteImgDao routeImgDao =new RouteImgDaoImpl();
    @Override
    public List<RouteImg> findRouteImgByRid(int rid) {
        List<RouteImg> imgs = routeImgDao.getRouteImgByRid(rid);
        if(imgs == null || imgs.size() == 0){
            return  new ArrayList<>();
        }
        return imgs;
    }
}
