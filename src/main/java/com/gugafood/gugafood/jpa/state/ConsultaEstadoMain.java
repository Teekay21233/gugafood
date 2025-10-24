package com.gugafood.gugafood.jpa.state;

import com.gugafood.gugafood.GugafoodApplication;
import com.gugafood.gugafood.domain.model.State;
import com.gugafood.gugafood.domain.repository.StateRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class ConsultaEstadoMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(GugafoodApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        StateRepository kr = applicationContext.getBean(StateRepository.class);

        List<State> states = kr.list();

//        for (State state: states){
//            System.out.println(state.getName());
//        }

        states.forEach(state -> System.out.println(state.getId() + " - " + state.getName()));
    }
}
