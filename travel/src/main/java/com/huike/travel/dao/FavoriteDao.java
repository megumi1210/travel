package com.huike.travel.dao;

import com.huike.travel.domain.Favorite;
import com.huike.travel.domain.PageParam;

import java.util.List;

public interface FavoriteDao {

    List<Favorite> findFavoriteByPage(int uid, PageParam pageParam);

    int getFavoriteTotalByUid( int uid);

    int  insertRecord(int rid ,int uid);

    int   findFavorite(int rid ,int uid);
}
