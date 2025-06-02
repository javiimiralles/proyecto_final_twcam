package com.uv.project.schedulers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(
	exclude = {DataSourceAutoConfiguration.class},
	scanBasePackages = {"com.uv.project.schedulers", "com.uv.project.shared"}
)
@EntityScan(basePackages = {"com.uv.project.shared.domain"})
@EnableScheduling
public class SchedulersApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchedulersApplication.class, args);
	}

}
