package com.example.topjava.initDB;

import com.example.topjava.domain.Dish;
import com.example.topjava.domain.Menu;
import com.example.topjava.domain.Restaurant;
import com.example.topjava.repository.DishRepository;
import com.example.topjava.repository.MenuRepository;
import com.example.topjava.repository.RestaurantRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;

@Component
public class DataLoader implements ApplicationRunner {
    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;
    private final DishRepository dishRepository;

    public DataLoader(RestaurantRepository restaurantRepository, MenuRepository menuRepository, DishRepository dishRepository) {
        this.restaurantRepository = restaurantRepository;
        this.menuRepository = menuRepository;
        this.dishRepository = dishRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Restaurant pizzaStore = restaurantRepository.save(new Restaurant("pizza_store"));
        Restaurant sushiStore = restaurantRepository.save(new Restaurant("sushi_store"));
        Restaurant kfc = restaurantRepository.save(new Restaurant("kfc"));

        ArrayList<Dish> pizzaStoreDishes = new ArrayList<>();
        Menu pizzaStoreMenu = menuRepository.save(new Menu(LocalDate.now(), pizzaStoreDishes, pizzaStore));
        pizzaStoreDishes.add(dishRepository.save(new Dish("pizza", 100, pizzaStoreMenu)));
        pizzaStoreDishes.add(dishRepository.save(new Dish("coffee", 50, pizzaStoreMenu)));
        pizzaStoreDishes.add(dishRepository.save(new Dish("", 100, pizzaStoreMenu)));

        ArrayList<Dish> sushiStoreDishes = new ArrayList<>();
        Menu sushiStoreMenu = menuRepository.save(new Menu(LocalDate.now(), sushiStoreDishes, sushiStore));
        sushiStoreDishes.add(dishRepository.save(new Dish("sushi", 100, sushiStoreMenu)));
        sushiStoreDishes.add(dishRepository.save(new Dish("tea", 50, sushiStoreMenu)));
        sushiStoreDishes.add(dishRepository.save(new Dish("", 100, sushiStoreMenu)));

        ArrayList<Dish> kfsStoreDishes = new ArrayList<>();
        Menu kfsMenu = menuRepository.save(new Menu(LocalDate.now(), kfsStoreDishes, kfc));
        kfsStoreDishes.add(dishRepository.save(new Dish("burger", 100, kfsMenu)));
        kfsStoreDishes.add(dishRepository.save(new Dish("cola", 50, kfsMenu)));
        kfsStoreDishes.add(dishRepository.save(new Dish("", 100, kfsMenu)));
    }
}
