package com.gugafood.gugafood.jpa;

import com.gugafood.gugafood.GugafoodApplication;
import com.gugafood.gugafood.domain.model.Kitchen;
import com.gugafood.gugafood.domain.model.Restaurant;
import com.gugafood.gugafood.domain.repository.KitchenRepository;
import com.gugafood.gugafood.domain.repository.RestaurantRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class RemocaoRestauranteMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(GugafoodApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        RestaurantRepository rr = applicationContext.getBean(RestaurantRepository.class);

        Restaurant restaurant = new Restaurant();
        restaurant.setId(1L);

        rr.delete(restaurant);

        List<Restaurant> restaurants = rr.list();
        restaurants.forEach(Restaurant -> System.out.println(restaurant.getId() + " - " + restaurant.getName()));

    }
}
