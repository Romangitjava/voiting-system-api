package com.example.topjava.controller;

import com.example.topjava.domain.Dish;
import com.example.topjava.domain.Menu;
import com.example.topjava.exception.ResourceNotFoundException;
import com.example.topjava.repository.DishRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("restaurants/{restaurant_id}/menu/{menu_id}/dishes")
public class DishController {
    private final DishRepository dishRepository;

    public DishController(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @GetMapping()
    public List<Dish> getDishesByMenuId(@PathVariable("menu_id") Long MenuId) {
        return dishRepository.findDishByMenuId(MenuId);
    }

    @GetMapping(value = "{id}")
    public Dish getMealById(@PathVariable("id") Long id) {
        return dishRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(id));
    }

    @PostMapping
    public Dish create(@Valid @PathVariable("menu_id") Menu menu, @RequestBody Dish dish) {
        dish.setMenu(menu);
        return  dishRepository.save(dish);
    }

    @PutMapping(value = "{id}")
    public Dish update(@PathVariable("id") Dish dishFromDb,
                       @PathVariable("menu_id") Menu menu,
                       @Valid @RequestBody Dish dish) {
        BeanUtils.copyProperties(dish, dishFromDb, "id");
        dish.setMenu(menu);
        return dishRepository.save(dishFromDb);
    }

    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable("id") Dish dish) {
        dishRepository.delete(dish);
    }
}

