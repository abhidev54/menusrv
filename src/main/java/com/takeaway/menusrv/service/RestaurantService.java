package com.takeaway.menusrv.service;

import com.takeaway.menusrv.model.Restaurant;

public interface RestaurantService {
    void saveRestaurantInfomation(Restaurant restaurant);

    Restaurant findRestaurantByName(String name);
}
