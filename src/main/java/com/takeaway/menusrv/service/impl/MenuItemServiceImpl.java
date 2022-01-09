package com.takeaway.menusrv.service.impl;

import com.takeaway.menusrv.model.MenuItem;
import com.takeaway.menusrv.repository.MenuItemsRepository;
import com.takeaway.menusrv.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemServiceImpl implements MenuItemService {
    private MenuItemsRepository menuItemsRepository;

    @Autowired
    public MenuItemServiceImpl(MenuItemsRepository menuItemsRepository){
        this.menuItemsRepository = menuItemsRepository;
    }

    @Override
    public void saveMenuItem(MenuItem menuItem) {
        menuItemsRepository.save(menuItem);
    }

    @Override
    public void saveMenuItemList(List<MenuItem> menuItemList) {
        menuItemsRepository.saveAll(menuItemList);
    }

    @Override
    public MenuItem getMenuItemByName(String name) {
        return menuItemsRepository.findByName(name);
    }

    @Override
    public List<MenuItem> getAllMenuItemsByRestaurantId(String rid) {
        return menuItemsRepository.findAllByRestaurantId(rid);
    }
}
