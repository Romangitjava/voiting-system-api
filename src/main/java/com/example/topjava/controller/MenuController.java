package com.example.topjava.controller;

import com.example.topjava.domain.Menu;
import com.example.topjava.domain.Restaurant;
import com.example.topjava.exception.ResourceNotFoundException;
import com.example.topjava.repository.MenuRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("restaurants/{restaurant_id}/menu")
public class MenuController {

    private final MenuRepository menuRepository;

    public MenuController(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @GetMapping
    public List<Menu> getMenuByRestaurantId(@PathVariable("restaurant_id") Long restaurant_id) {
        return menuRepository.findMenuByRestaurantId(restaurant_id);
    }

    @GetMapping(value = "{id}")
    public Menu getMenuById(@PathVariable("id") Long id) {
        return menuRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @PostMapping
    public Menu create(@PathVariable("restaurant_id")Restaurant restaurant,
                       @Valid @RequestBody Menu menu) {
        menu.setRestaurant(restaurant);
        return  menuRepository.save(menu);
    }

    @PutMapping(value = "{id}")
    public Menu update(@PathVariable("id") Menu menuFromDb,
                       @PathVariable("restaurant_id") Restaurant restaurant,
                       @Valid @RequestBody Menu menu) {

        BeanUtils.copyProperties(menu, menuFromDb, "id");
        menuFromDb.setRestaurant(restaurant);
        return menuRepository.save(menuFromDb);
    }

    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable("id") Menu menu) {
        menuRepository.delete(menu);
    }
}

