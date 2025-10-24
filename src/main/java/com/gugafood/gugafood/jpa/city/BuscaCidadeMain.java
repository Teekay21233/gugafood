package com.gugafood.gugafood.jpa.city;

import com.gugafood.gugafood.GugafoodApplication;
import com.gugafood.gugafood.domain.model.City;
import com.gugafood.gugafood.domain.repository.CityRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class BuscaCidadeMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(GugafoodApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
        CityRepository kr = applicationContext.getBean(CityRepository.class);

        City city = kr.findById(1L);

        System.out.println(city.getName());
    }
}
