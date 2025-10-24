package com.gugafood.gugafood.jpa.restaurant;

import com.gugafood.gugafood.GugafoodApplication;
import com.gugafood.gugafood.domain.model.Restaurant;
import com.gugafood.gugafood.domain.repository.RestaurantRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class ConsultaRestauranteMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(GugafoodApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        RestaurantRepository rr = applicationContext.getBean(RestaurantRepository.class);

        List<Restaurant> restaurants = rr.list();

//        for (Kitchen kitchen: kitchens){
//            System.out.println(kitchen.getName());
//        }

        restaurants.forEach(restaurant -> System.out.println(restaurant.getName() + " - " + restaurant.getTaxaFrete() + " - " + restaurant.getKitchen().getName()));
    }
}
