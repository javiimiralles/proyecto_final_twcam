package com.uv.project.city_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(
	exclude = {DataSourceAutoConfiguration.class},
	scanBasePackages = {"com.uv.project.city_service", "com.uv.project.shared"}
)
@EnableScheduling
public class CityServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CityServiceApplication.class, args);
	}

}
