package com.gugafood.gugafood.jpa.city;

import com.gugafood.gugafood.GugafoodApplication;
import com.gugafood.gugafood.domain.model.City;
import com.gugafood.gugafood.domain.repository.CityRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class ConsultaCidadeMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(GugafoodApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CityRepository kr = applicationContext.getBean(CityRepository.class);

        List<City> citys = kr.list();

//        for (City city: cities){
//            System.out.println(city.getName());
//        }

        citys.forEach(city -> System.out.println(city.getId() + " - " + city.getName()));
    }
}
