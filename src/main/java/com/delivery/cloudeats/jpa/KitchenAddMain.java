package com.delivery.cloudeats.jpa;

import com.delivery.cloudeats.CloudeatsApiApplication;
import com.delivery.cloudeats.domain.model.Kitchen;
import com.delivery.cloudeats.domain.repository.KitchenRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class KitchenAddMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(CloudeatsApiApplication.class)
                                                        .web(WebApplicationType.NONE)
                                                        .run(args);
        KitchenRepository kitchenRepository = applicationContext.getBean(KitchenRepository.class);

        Kitchen kitchen1 = new Kitchen();
        kitchen1.setName("Brazilian");

        Kitchen kitchen2 = new Kitchen();
        kitchen2.setName("Japanese");

//        kitchen1 = kitchenRepository.add(kitchen1);
//        kitchen2 = kitchenRepository.add(kitchen2);

        System.out.printf("%d - %s\n", kitchen1.getId(), kitchen1.getName());
        System.out.printf("%d - %s\n", kitchen2.getId(), kitchen2.getName());

    }
}
