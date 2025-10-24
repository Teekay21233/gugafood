package com.gugafood.gugafood.jpa.permission;

import com.gugafood.gugafood.GugafoodApplication;
import com.gugafood.gugafood.domain.model.Permission;
import com.gugafood.gugafood.domain.repository.PermissionRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class ConsultaPermissaoMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(GugafoodApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        PermissionRepository kr = applicationContext.getBean(PermissionRepository.class);

        List<Permission> permissions = kr.list();

//        for (Permission permission: permissions){
//            System.out.println(permission.getName());
//        }

        permissions.forEach(permission -> System.out.println(permission.getId() + " - " + permission.getName()));
    }
}
