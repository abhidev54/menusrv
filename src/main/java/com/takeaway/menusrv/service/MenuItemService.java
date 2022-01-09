package com.takeaway.menusrv.service;

import com.takeaway.menusrv.model.MenuItem;

import java.util.List;

public interface MenuItemService {
    void saveMenuItem(MenuItem menuItem);

    void saveMenuItemList(List<MenuItem> menuItemList);

    MenuItem getMenuItemByName(String name);

    List<MenuItem> getAllMenuItemsByRestaurantId(String rid);
}
