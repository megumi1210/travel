package com.huike.travel.service.impl;

import com.huike.travel.dao.SellerDao;
import com.huike.travel.dao.impl.SellerDaoImpl;
import com.huike.travel.domain.Seller;
import com.huike.travel.service.SellerService;

public class SellerServiceImpl implements SellerService {

    private SellerDao sellerDao = new SellerDaoImpl();

    @Override
    public Seller findSellerBySid(int sid) {
        return sellerDao.findSellerBySid(sid);
    }
}
