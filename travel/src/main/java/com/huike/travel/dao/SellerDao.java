package com.huike.travel.dao;

import com.huike.travel.domain.Seller;

public interface SellerDao {

    Seller   findSellerBySid(int sid);
}
