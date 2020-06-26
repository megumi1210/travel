package com.huike.travel.dao;

import com.huike.travel.domain.Category;

import java.util.List;

public interface CategoryDao {

    Category  findCategoryByCid(int cid);

    List<Category> findAll();
}
