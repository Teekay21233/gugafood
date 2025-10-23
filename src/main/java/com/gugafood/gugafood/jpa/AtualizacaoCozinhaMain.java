package com.gugafood.gugafood.jpa;

import com.gugafood.gugafood.GugafoodApplication;
import com.gugafood.gugafood.domain.model.Kitchen;
import com.gugafood.gugafood.domain.repository.KitchenRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class AtualizacaoCozinhaMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(GugafoodApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

       KitchenRepository kr = applicationContext.getBean(KitchenRepository.class);

        Kitchen kitchen1 = new Kitchen();
        kitchen1.setId(1L);
        kitchen1.setName("Brasileira");

        kr.update(kitchen1);

        List<Kitchen> kitchens = kr.list();
        kitchens.forEach(kitchen -> System.out.println(kitchen.getId() + " - " + kitchen.getName()));

    }
}
