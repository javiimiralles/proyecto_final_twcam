package com.uv.project.bike_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@EnableFeignClients
@SpringBootApplication(
	exclude = {
		DataSourceAutoConfiguration.class,
		MongoAutoConfiguration.class,
		MongoDataAutoConfiguration.class
	},
	scanBasePackages = {"com.uv.project.bike_service", "com.uv.project.shared"}
)
@EntityScan(basePackages = {"com.uv.project.shared.domain"})
@OpenAPIDefinition(
	info = @Info(
		title = "Bike Service API",
		version = "1.0.0",
		description = "API para la gestión de bicicletas"
	),
	servers = @Server(
		url = "http://localhost:8080",
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
