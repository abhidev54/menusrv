package com.takeaway.menusrv.controller;

import com.takeaway.menusrv.model.Category;
import com.takeaway.menusrv.model.MenuItem;
import com.takeaway.menusrv.model.Restaurant;
import com.takeaway.menusrv.service.impl.CategoryServiceImpl;
import com.takeaway.menusrv.service.impl.MenuItemServiceImpl;
import com.takeaway.menusrv.service.impl.RestaurantServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menusrv/api/")
public class CategoryController {

    private CategoryServiceImpl categoryService;

    @Autowired
    public void CategoryController(CategoryServiceImpl categoryService){
        this.categoryService = categoryService;
    }

    @PostMapping("/category")
    @ResponseStatus(HttpStatus.CREATED)
    public void uploadCategorynfo(@RequestBody Category category){
        categoryService.saveCategoryInfomation(category);
    }

    @GetMapping("/category")
    public Category getCategoryByName(@RequestParam(value = "name") String name){
        return categoryService.findCategoryByName(name);
    }
}
