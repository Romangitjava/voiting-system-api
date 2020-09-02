package com.example.topjava.controller;

import com.example.topjava.domain.Restaurant;
import com.example.topjava.domain.RestaurantRating;
import com.example.topjava.domain.User;
import com.example.topjava.exception.ResourceNotFoundException;
import com.example.topjava.exception.VoitingTimeIsOverException;
import com.example.topjava.repository.RestaurantRatingRepository;
import com.example.topjava.repository.UserRepository;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("restaurants/{restaurant_id}/ratings")
public class RatingController {

    private final RestaurantRatingRepository restaurantRatingRepository;
    private final UserRepository userRepository;

    public RatingController(RestaurantRatingRepository restaurantRatingRepository, UserRepository userRepository) {
        this.restaurantRatingRepository = restaurantRatingRepository;
        this.userRepository = userRepository;
    }

    @GetMapping()
    public List<RestaurantRating> getRatingByRestaurantId(@PathVariable("restaurant_id") Long restaurant_id) {
        return restaurantRatingRepository.findRatingByRestaurantId(restaurant_id);
    }

    @GetMapping(value = "{id}")
    public RestaurantRating getRatingById(@PathVariable("id") Long id) {
        return restaurantRatingRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(id));
    }

    @PostMapping
    public RestaurantRating createOrUpdate(@Valid @RequestBody RestaurantRating restaurantRating,
                                   @PathVariable("restaurant_id") Restaurant restaurant, Principal principal) {

        if (!LocalTime.now().isBefore(LocalTime.of(11, 0, 0)))
            throw new VoitingTimeIsOverException();

        User currentUser = userRepository.findByUsername(principal.getName());
        RestaurantRating restaurantRatingFromDb = restaurantRatingRepository.findRatingByUser(currentUser);
        restaurantRating.setRestaurant(restaurant);
        restaurantRating.setUser(currentUser);

        if (restaurantRatingFromDb != null) {
            BeanUtils.copyProperties(restaurantRating, restaurantRatingFromDb, "id");
            return restaurantRatingRepository.save(restaurantRatingFromDb);
        }

        else return restaurantRatingRepository.save(restaurantRating);
    }

    @PutMapping(value = "{id}")
    public RestaurantRating update(@PathVariable("id") RestaurantRating ratingFromDb, @Valid @RequestBody RestaurantRating restaurantRating) {
        BeanUtils.copyProperties(restaurantRating, ratingFromDb, "id");
        return restaurantRatingRepository.save(ratingFromDb);
    }

    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable("id") RestaurantRating restaurantRating) {
        restaurantRatingRepository.delete(restaurantRating);
    }
}

