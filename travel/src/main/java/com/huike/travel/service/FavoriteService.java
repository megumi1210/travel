package com.huike.travel.service;


import com.huike.travel.domain.PageInfo;
import com.huike.travel.domain.PageParam;
import com.huike.travel.domain.Route;

public interface FavoriteService {

    PageInfo<Route> findFavoriteRoutesByPage(int uid, PageParam pageParam);

    boolean  insertRecord(int rid ,int uid);

    boolean  alreadyStared(int rid ,int uid);

}
