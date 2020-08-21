package com.example.topjava.controller;

import com.example.topjava.domain.Restaurant;
import com.example.topjava.repository.RestaurantRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("restaurants")
public class RestaurantController {
    private final RestaurantRepository restaurantRepository;

    public RestaurantController(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping
    public Iterable<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }

    @GetMapping(value = "{id}")
    public Optional<Restaurant> getOne(@PathVariable("id") Long id) {
        return restaurantRepository.findById(id);
    }

    @PostMapping
    public Restaurant create(@RequestBody Restaurant restaurant) {
        return  restaurantRepository.save(restaurant);
    }

    @PutMapping(value = "{id}")
    public Restaurant update(@PathVariable("id") Restaurant fromDb,
                             @RequestBody Restaurant restaurant) {
        BeanUtils.copyProperties(restaurant, fromDb, "id");

        return restaurantRepository.save(fromDb);
    }

    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable("id") Restaurant restaurant) {
        restaurantRepository.delete(restaurant);
    }


}
