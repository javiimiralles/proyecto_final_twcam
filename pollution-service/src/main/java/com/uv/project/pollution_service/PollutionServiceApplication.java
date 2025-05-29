package com.uv.project.pollution_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = {"com.uv.project.pollution_service", "com.uv.project.shared"})
@EntityScan(basePackages = {"com.uv.project.shared.domain"})
public class PollutionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PollutionServiceApplication.class, args);
	}

}
