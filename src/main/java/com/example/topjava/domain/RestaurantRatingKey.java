package com.example.topjava.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RestaurantRatingKey implements Serializable {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "restautant_id")
    private Long restaurantId;

    public RestaurantRatingKey() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RestaurantRatingKey)) return false;
        RestaurantRatingKey that = (RestaurantRatingKey) o;
        return Objects.equals(getUserId(), that.getUserId()) &&
                Objects.equals(getRestaurantId(), that.getRestaurantId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getRestaurantId());
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }
}
