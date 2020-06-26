package com.huike.travel.service.impl;

import com.huike.travel.dao.FavoriteDao;
import com.huike.travel.dao.impl.FavoriteDaoImpl;
import com.huike.travel.domain.Favorite;
import com.huike.travel.domain.PageInfo;
import com.huike.travel.domain.PageParam;
import com.huike.travel.domain.Route;
import com.huike.travel.service.FavoriteService;


import java.util.ArrayList;
import java.util.List;

public class FavoriteServiceImpl implements FavoriteService {


    private FavoriteDao favoriteDao = new FavoriteDaoImpl();

    @Override
    public PageInfo<Route> findFavoriteRoutesByPage(int uid, PageParam pageParam) {
        List<Favorite> favorites = favoriteDao.findFavoriteByPage(uid,pageParam);
        List<Route> routes = new ArrayList<>();
        if(favorites != null && favorites.size() > 0){
            for(Favorite f :favorites){
                Route r  =f.getRoute();
                if(r != null){
                    routes.add(r);
                }
            }
        }

        int count = favoriteDao.getFavoriteTotalByUid(uid);

       return PageInfo.of(routes,pageParam,count);

    }

    @Override
    public boolean insertRecord(int rid, int uid) {
        return  favoriteDao.insertRecord(rid,uid) ==1;
    }

    @Override
    public boolean alreadyStared(int rid, int uid) {
        return  favoriteDao.findFavorite(rid ,uid) > 0;
    }
}
