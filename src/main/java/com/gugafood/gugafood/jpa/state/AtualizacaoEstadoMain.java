package com.gugafood.gugafood.jpa.state;

import com.gugafood.gugafood.GugafoodApplication;
import com.gugafood.gugafood.domain.model.City;
import com.gugafood.gugafood.domain.repository.CityRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class AtualizacaoEstadoMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(GugafoodApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

       CityRepository cr = applicationContext.getBean(CityRepository.class);

        City city1 = new City();
        city1.setId(1L);
        city1.setName("Angra dos Reis");

        cr.update(city1);

        List<City> cities = cr.list();
        cities.forEach(city -> System.out.println(city.getId() + " - " + city.getName()));

    }
}
