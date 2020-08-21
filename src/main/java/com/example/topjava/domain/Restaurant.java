package com.example.topjava.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "restaurant")
    private List<Menu> menu;

    @OneToMany(mappedBy = "restaurant")
    private Set<RestaurantRating> ratings;

    public Restaurant() {
    }

    public Restaurant(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", menu=" + menu +
                ", ratings=" + ratings +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Menu> getMenu() {
        return menu;
    }

    public void setMenu(List<Menu> menu) {
        this.menu = menu;
    }

    public Set<RestaurantRating> getRatings() {
        return ratings;
    }

    public void setRatings(Set<RestaurantRating> ratings) {
        this.ratings = ratings;
    }

}
