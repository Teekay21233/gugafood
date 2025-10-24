package com.gugafood.gugafood.jpa.paymentMethod;

import com.gugafood.gugafood.GugafoodApplication;
import com.gugafood.gugafood.domain.model.PaymentMethod;
import com.gugafood.gugafood.domain.repository.PaymentMethodRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class ConsultaFormaPagamentoMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(GugafoodApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        PaymentMethodRepository kr = applicationContext.getBean(PaymentMethodRepository.class);

        List<PaymentMethod> paymentMethods = kr.list();

//        for (PaymentMethod paymentMethod: paymentMethods){
//            System.out.println(paymentMethod.getName());
//        }

        paymentMethods.forEach(paymentMethod -> System.out.println(paymentMethod.getId() + " - " + paymentMethod.getDescription()));
    }
}
