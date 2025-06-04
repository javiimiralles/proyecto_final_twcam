package com.uv.project.city_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication(
	exclude = {DataSourceAutoConfiguration.class},
	scanBasePackages = {"com.uv.project.city_service", "com.uv.project.shared"}
)
@EntityScan(basePackages = {"com.uv.project.shared.domain"})
@EnableScheduling
@OpenAPIDefinition(
	info = @Info(
		title = "City Service API",
		version = "1.0.0",
		description = "API para el ayuntamiento"
	),
	servers = @Server(
		url = "http://localhost:8082",
		description = "Local server"
	)
)
@SecurityScheme(
	name = "Bearer Auth",
	type = SecuritySchemeType.HTTP,
	bearerFormat = "JWT",
	scheme = "bearer"
)
public class CityServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CityServiceApplication.class, args);
	}

}
