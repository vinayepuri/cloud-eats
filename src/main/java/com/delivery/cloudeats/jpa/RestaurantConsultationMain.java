package com.delivery.cloudeats.jpa;

import com.delivery.cloudeats.CloudeatsApiApplication;
import com.delivery.cloudeats.domain.repository.RestaurantRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class RestaurantConsultationMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(CloudeatsApiApplication.class)
                                                        .web(WebApplicationType.NONE)
                                                        .run(args);
        RestaurantRepository restaurantRepository = applicationContext.getBean(RestaurantRepository.class);

    }
}
