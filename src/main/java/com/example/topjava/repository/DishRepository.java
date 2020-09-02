package com.example.topjava.repository;

import com.example.topjava.domain.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DishRepository extends JpaRepository<Dish, Long> {
    List<Dish> findDishByMenuId(Long restaurant_id);
}
