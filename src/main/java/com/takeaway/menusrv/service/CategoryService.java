package com.takeaway.menusrv.service;

import com.takeaway.menusrv.model.Category;

public interface CategoryService {
    void saveCategoryInfomation(Category category);

    Category findCategoryByName(String name);
}
