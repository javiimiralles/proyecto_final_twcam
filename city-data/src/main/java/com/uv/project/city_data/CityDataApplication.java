package com.uv.project.city_data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = {"com.uv.project.city_data", "com.uv.project.shared"})
@EntityScan(basePackages = {"com.uv.project.shared.domain"})
public class CityDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(CityDataApplication.class, args);
	}

}
