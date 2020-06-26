package com.huike.travel.service.impl;

import com.huike.travel.dao.CategoryDao;
import com.huike.travel.dao.impl.CategoryDaoImpl;
import com.huike.travel.dao.impl.RouteDaoImpl;
import com.huike.travel.domain.Category;
import com.huike.travel.service.CategoryService;
import com.huike.travel.service.RouteService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao = new CategoryDaoImpl();


    @Override
    public Category findCategoryByCid(int cid) {
        return categoryDao.findCategoryByCid(cid);
    }

    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }
}
