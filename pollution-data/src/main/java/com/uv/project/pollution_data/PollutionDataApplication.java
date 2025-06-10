package com.uv.project.pollution_data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = {"com.uv.project.pollution_data", "com.uv.project.shared"})
@EntityScan(basePackages = {"com.uv.project.shared.domain"})
public class PollutionDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(PollutionDataApplication.class, args);
	}

}
