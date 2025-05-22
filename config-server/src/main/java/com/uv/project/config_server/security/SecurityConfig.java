package com.uv.project.config_server.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())
                .httpBasic(withDefaults())
                .authorizeHttpRequests(requests -> requests
                    .requestMatchers(HttpMethod.GET, "/api/v1/aparcamientos", "/api/v1/aparcamientos/ranking", 
                                    "/api/v1/aparcamientos/**/status", "/api/v1/estaciones", "/api/v1/estacion/**/status",
                                    "/api/v1/aparcamientoCercano", "/api/v1/aggregatedData").permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/v1/aparcamiento", "/api/v1/estacion")
                        .hasAuthority("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/api/v1/aparcamiento/**", "/api/v1/estacion/**")
                        .hasAuthority("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/api/v1/aparcamiento/**", "/api/v1/estacion/**")
                        .hasAuthority("ADMIN")
                    .requestMatchers(HttpMethod.POST, "/api/v1/evento/**").hasAuthority("APARCAMIENTO")
                    .requestMatchers(HttpMethod.POST, "/api/v1/estacion/**").hasAuthority("ESTACION")
                    .requestMatchers(HttpMethod.GET, "/api/v1/aggregateData").hasAuthority("SERVICIO")
                    .anyRequest().authenticated()
                );

        return http.build();
    }

}
