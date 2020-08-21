package com.example.topjava.repository;

import com.example.topjava.domain.Menu;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends CrudRepository <Menu, Long>{
    List<Menu> findMenuByRestaurantId(Long id);
}
