package com.takeaway.menusrv.service.impl;

import com.takeaway.menusrv.model.Category;
import com.takeaway.menusrv.model.Restaurant;
import com.takeaway.menusrv.repository.CategoryRepository;
import com.takeaway.menusrv.repository.RestaurantRepository;
import com.takeaway.menusrv.service.CategoryService;
import com.takeaway.menusrv.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void saveCategoryInfomation(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public Category findCategoryByName(String name) {
        return categoryRepository.findFirstByName(name);
    }
}
