package com.example.topjava.controller;

import com.example.topjava.domain.Dish;
import com.example.topjava.domain.Menu;
import com.example.topjava.repository.DishRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("restaurants/{restaurant_id}/menu/{menu_id}/dish")
public class DishController {
    private final DishRepository dishRepository;

    public DishController(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @GetMapping()
    public List<Menu> getMenuByRestaurantId(@PathVariable("menu_id") Long restaurant_id) {
        return dishRepository.findDishByMenuId(restaurant_id);
    }

    @GetMapping(value = "{id}")
    public Optional<Dish> getMealById(@PathVariable("id") Long id) {
        return dishRepository.findById(id);
    }

    @PostMapping
    public Dish create(@RequestBody Dish dish) {
        return  dishRepository.save(dish);
    }

    @PutMapping(value = "{id}")
    public Dish update(@PathVariable("id") Dish fromDb, @RequestBody Dish dish) {
        BeanUtils.copyProperties(dish, fromDb, "id");
        return dishRepository.save(fromDb);
    }

    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable("id") Dish dish) {
        dishRepository.delete(dish);
    }
}

