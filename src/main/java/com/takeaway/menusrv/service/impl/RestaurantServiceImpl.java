package com.takeaway.menusrv.service.impl;

import com.takeaway.menusrv.model.Restaurant;
import com.takeaway.menusrv.repository.RestaurantRepository;
import com.takeaway.menusrv.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    private RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository){
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public void saveRestaurantInfomation(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant findRestaurantByName(String name) {
        return restaurantRepository.findFirstByName(name);
    }
}
