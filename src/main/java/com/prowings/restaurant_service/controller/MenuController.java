package com.prowings.restaurant_service.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.prowings.restaurant_service.model.Menu;
import com.prowings.restaurant_service.service.MenuService;

import java.util.List;

@RestController
@RequestMapping("/api/menus")
@RequiredArgsConstructor
@Slf4j
public class MenuController {

    private final MenuService menuService;

    @PostMapping("/{restaurantId}")
    public ResponseEntity<Menu> addMenuItem(@PathVariable Long restaurantId, @RequestBody Menu menu) {
        log.info("Adding new menu item '{}' to restaurant with ID: {}", menu.getItemName(), restaurantId);
        return ResponseEntity.ok(menuService.addMenuItem(restaurantId, menu));
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Menu>> getMenuByRestaurant(@PathVariable Long restaurantId) {
        log.info("Fetching menu for restaurant with ID: {}", restaurantId);
        return ResponseEntity.ok(menuService.getMenuByRestaurant(restaurantId));
    }

    @PutMapping("/{menuId}")
    public ResponseEntity<Menu> updateMenuItem(@PathVariable Long menuId, @RequestBody Menu menu) {
        log.info("Updating menu item with ID: {} and new name: {}", menuId, menu.getItemName());
        return ResponseEntity.ok(menuService.updateMenuItem(menuId, menu));
    }

    @DeleteMapping("/{menuId}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long menuId) {
        log.info("Deleting menu item with ID: {}", menuId);
        menuService.deleteMenuItem(menuId);
        return ResponseEntity.noContent().build();
    }
}
