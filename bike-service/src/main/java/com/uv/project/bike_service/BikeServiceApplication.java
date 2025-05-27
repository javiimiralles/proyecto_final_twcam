package com.uv.project.bike_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication(scanBasePackages = {"com.uv.project.bike_service", "com.uv.project.shared"})
@OpenAPIDefinition(
	info = @Info(
		title = "Bike Service API",
		version = "1.0.0",
		description = "API para la gestión de bicicletas"
	),
	servers = @Server(
		url = "http://localhost:8080/api/v1",
		description = "Local server"
	)
)
@SecurityScheme(
	name = "Bearer Auth",
	type = SecuritySchemeType.HTTP,
	bearerFormat = "JWT",
	scheme = "bearer"
)
public class BikeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BikeServiceApplication.class, args);
	}

}
