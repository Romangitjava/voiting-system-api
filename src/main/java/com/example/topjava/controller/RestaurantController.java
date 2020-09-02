package com.example.topjava.controller;

import com.example.topjava.domain.Restaurant;
import com.example.topjava.exception.ResourceNotFoundException;
import com.example.topjava.repository.RestaurantRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public Restaurant getOne(@PathVariable("id") Long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException(id));
        //curl -u user:000 -i -H 'Accept:application/json' http://localhost:8080/restaurants
    }

    @PostMapping
    public Restaurant create(@Valid @RequestBody Restaurant restaurant) {
        return  restaurantRepository.save(restaurant);
    }

    @PutMapping(value = "{id}")
    public Restaurant update(@PathVariable("id") Restaurant restaurantFromDb,
                             @Valid @RequestBody Restaurant restaurant) {
        BeanUtils.copyProperties(restaurant, restaurantFromDb, "id");

        return restaurantRepository.save(restaurantFromDb);
    }

    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable("id") Restaurant restaurant) {
        restaurantRepository.delete(restaurant);
    }

}
