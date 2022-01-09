package com.takeaway.menusrv.controller;

import com.takeaway.menusrv.model.MenuItem;
import com.takeaway.menusrv.model.Restaurant;
import com.takeaway.menusrv.service.impl.MenuItemServiceImpl;
import com.takeaway.menusrv.service.impl.RestaurantServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menusrv/api/")
public class RestaurantController {

    private MenuItemServiceImpl menuItemService;
    private RestaurantServiceImpl restaurantService;

    @Autowired
    public void RestaurantServiceRestController(
            MenuItemServiceImpl menuItemService,
            RestaurantServiceImpl restaurantService
    ){
        this.menuItemService = menuItemService;
        this.restaurantService = restaurantService;
    }

    @PostMapping("/restaurants")
    @ResponseStatus(HttpStatus.CREATED)
    public void uploadRestaurantInfo(@RequestBody Restaurant restaurant){
        restaurantService.saveRestaurantInfomation(restaurant);
    }

    @GetMapping("/restaurants")
    public Restaurant getRestaurantByName(@RequestParam(value = "name") String name){
        return restaurantService.findRestaurantByName(name);
    }

    @PostMapping("/restaurants/{rid}/menuItem")
    @ResponseStatus(HttpStatus.CREATED)
    public void uploadMenuItem(@RequestBody MenuItem menuItem){
        menuItemService.saveMenuItem(menuItem);
    }

    @PostMapping("/restaurants/bulk/menuItem")
    @ResponseStatus(HttpStatus.CREATED)
    public void uploadMenuItemList(@RequestBody List<MenuItem> menuItemList){
        menuItemService.saveMenuItemList(menuItemList);
    }

    @GetMapping("/restaurants/{rid}/menuItem")
    public List<MenuItem> getAllMenusByRestaurantId(@PathVariable String rid){
        return menuItemService.getAllMenuItemsByRestaurantId(rid);
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return new ResponseEntity(HttpStatus.OK);
    }
}
