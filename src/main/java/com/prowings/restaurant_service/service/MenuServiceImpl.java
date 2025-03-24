package com.prowings.restaurant_service.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prowings.restaurant_service.model.Menu;
import com.prowings.restaurant_service.model.Restaurant;
import com.prowings.restaurant_service.repository.MenuRepository;
import com.prowings.restaurant_service.repository.RestaurantRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

	@Autowired
    private final MenuRepository menuRepository;
	@Autowired
    private final RestaurantRepository restaurantRepository;

    @Override
    public Menu addMenuItem(Long restaurantId, Menu menu) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found with ID: " + restaurantId));
        menu.setRestaurant(restaurant);
        return menuRepository.save(menu);
    }

    @Override
    public List<Menu> getMenuByRestaurant(Long restaurantId) {
        if (!restaurantRepository.existsById(restaurantId)) {
            throw new EntityNotFoundException("Restaurant not found with ID: " + restaurantId);
        }
        return menuRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public Menu updateMenuItem(Long menuId, Menu updatedMenu) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new EntityNotFoundException("Menu not found with ID: " + menuId));
        menu.setItemName(updatedMenu.getItemName());
        menu.setPrice(updatedMenu.getPrice());
        menu.setAvailable(updatedMenu.isAvailable());
        return menuRepository.save(menu);
    }

    @Override
    public void deleteMenuItem(Long menuId) {
        if (!menuRepository.existsById(menuId)) {
            throw new EntityNotFoundException("Menu not found with ID: " + menuId);
        }
        menuRepository.deleteById(menuId);
    }
}
