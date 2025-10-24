package com.gugafood.gugafood.jpa.restaurant;

import com.gugafood.gugafood.GugafoodApplication;
import com.gugafood.gugafood.domain.model.Restaurant;
import com.gugafood.gugafood.domain.repository.RestaurantRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;
import java.util.List;

public class InclusaoRestauranteMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(GugafoodApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        RestaurantRepository rr = applicationContext.getBean(RestaurantRepository.class);

        Restaurant restaurant1 = new Restaurant();
        restaurant1.setName("Vila Ara");
        restaurant1.setTaxaFrete(BigDecimal.valueOf(50));

        Restaurant restaurant2 = new Restaurant();
        restaurant2.setName("Casa de Araça");
        restaurant2.setTaxaFrete(BigDecimal.valueOf(30));

        rr.add(restaurant1);
        rr.add(restaurant2);

        List<Restaurant> kitchens = rr.list();
        kitchens.forEach(kitchen -> System.out.println(kitchen.getId() + " - " + kitchen.getName()));

    }
}
