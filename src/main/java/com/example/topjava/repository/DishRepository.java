package com.example.topjava.repository;

import com.example.topjava.domain.Dish;
import com.example.topjava.domain.Menu;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DishRepository extends CrudRepository<Dish, Long> {
    List<Menu> findDishByMenuId(Long restaurant_id);
}
