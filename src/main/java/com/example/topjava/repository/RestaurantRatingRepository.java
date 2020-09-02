package com.example.topjava.repository;

import com.example.topjava.domain.RestaurantRating;
import com.example.topjava.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRatingRepository extends JpaRepository<RestaurantRating, Long> {
    List<RestaurantRating> findRatingByRestaurantId(Long restaurant_id);

    RestaurantRating findRatingByUser(User currentUser);
}
