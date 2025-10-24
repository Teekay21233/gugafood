package com.gugafood.gugafood.jpa.cozinha;

import com.gugafood.gugafood.GugafoodApplication;
import com.gugafood.gugafood.domain.model.Kitchen;
import com.gugafood.gugafood.domain.repository.KitchenRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class BuscaCozinhaMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(GugafoodApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
        KitchenRepository kr = applicationContext.getBean(KitchenRepository.class);

        Kitchen kitchen = kr.findById(1L);

        System.out.println(kitchen.getName());
    }
}
