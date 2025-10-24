package com.gugafood.gugafood.jpa.state;

import com.gugafood.gugafood.GugafoodApplication;
import com.gugafood.gugafood.domain.model.State;
import com.gugafood.gugafood.domain.repository.StateRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class BuscaEstadoMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(GugafoodApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
        StateRepository sr = applicationContext.getBean(StateRepository.class);

        State state = sr.findById(1L);

        System.out.println(state.getName());
    }
}
