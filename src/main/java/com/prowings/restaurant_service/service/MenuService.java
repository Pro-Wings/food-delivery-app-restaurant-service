package com.prowings.restaurant_service.service;

import java.util.List;

import com.prowings.restaurant_service.model.Menu;

public interface MenuService {

    Menu addMenuItem(Long restaurantId, Menu menu);

    List<Menu> getMenuByRestaurant(Long restaurantId);

    Menu updateMenuItem(Long menuId, Menu menu);

    void deleteMenuItem(Long menuId);
}
