package com.huike.travel.service;

import com.huike.travel.domain.Category;

import java.util.List;

public interface CategoryService {

    Category  findCategoryByCid(int cid);

    List<Category>  findAll();
}
