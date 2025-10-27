package com.gugafood.gugafood.domain.repository;

import com.gugafood.gugafood.domain.model.Kitchen;
import com.gugafood.gugafood.domain.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {
    List<Restaurant> list();
    Restaurant findById(Long id);
    Restaurant add(Restaurant restaurant);
    Restaurant update(Restaurant restaurant);
    void delete(Long id);
}
