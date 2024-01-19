package com.delivery.cloudeats;

import com.delivery.cloudeats.infrastructure.repository.CustomJpaRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class CloudeatsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudeatsApiApplication.class, args);
	}

}
