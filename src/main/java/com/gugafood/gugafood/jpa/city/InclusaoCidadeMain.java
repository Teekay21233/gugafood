package com.gugafood.gugafood.jpa.city;

import com.gugafood.gugafood.GugafoodApplication;
import com.gugafood.gugafood.domain.model.City;
import com.gugafood.gugafood.domain.repository.CityRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class InclusaoCidadeMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(GugafoodApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CityRepository kr = applicationContext.getBean(CityRepository.class);

        City city1 = new City();
        city1.setName("Lidice");

        City city2 = new City();
        city2.setName("Santa Cruz");

        kr.add(city1);
        kr.add(city2);

        List<City> citys = kr.list();
        citys.forEach(city -> System.out.println(city.getId() + " - " + city.getName()));

    }
}
