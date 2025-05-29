package com.uv.project.pollution_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.uv.project.pollution_service", "com.uv.project.shared"})
public class PollutionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PollutionServiceApplication.class, args);
	}

}
