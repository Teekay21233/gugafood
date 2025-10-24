package com.gugafood.gugafood.jpa.cozinha;

import com.gugafood.gugafood.GugafoodApplication;
import com.gugafood.gugafood.domain.model.Kitchen;
import com.gugafood.gugafood.domain.repository.KitchenRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.List;

public class ConsultaCozinhaMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(GugafoodApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        KitchenRepository kr = applicationContext.getBean(KitchenRepository.class);

        List<Kitchen> kitchens = kr.list();

//        for (Kitchen kitchen: kitchens){
//            System.out.println(kitchen.getName());
//        }

        kitchens.forEach(kitchen -> System.out.println(kitchen.getName()));
    }
}
