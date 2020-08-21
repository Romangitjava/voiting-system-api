package com.example.topjava.controller;

import com.example.topjava.domain.Menu;
import com.example.topjava.repository.MenuRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("restaurants/{restaurant_id}/menu")
public class MenuController {

    private final MenuRepository menuRepository;

    public MenuController(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @GetMapping()
    public List<Menu> getMenuByRestaurantId(@PathVariable("restaurant_id") Long restaurant_id) {
        return menuRepository.findMenuByRestaurantId(restaurant_id);
    }

    @GetMapping(value = "{id}")
    public Optional<Menu> getMealById(@PathVariable("id") Long id) {
        return menuRepository.findById(id);
    }

    @PostMapping
    public Menu create(@RequestBody Menu menu) {
        return  menuRepository.save(menu);
    }

    @PutMapping(value = "{id}")
    public Menu update(@PathVariable("id") Menu fromDb, @RequestBody Menu menu) {
        BeanUtils.copyProperties(menu, fromDb, "id");
        return menuRepository.save(fromDb);
    }

    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable("id") Menu menu) {
        menuRepository.delete(menu);
    }
}

