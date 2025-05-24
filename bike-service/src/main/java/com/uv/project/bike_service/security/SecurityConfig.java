package com.uv.project.bike_service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.uv.project.shared.domain.JwtConverter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtConverter jwtConverter;
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/api/v1/aparcamientos",
                    "/api/v1/aparcamientos/ranking",
                    "/api/v1/aparcamiento/*/status"
                ).permitAll()

                .requestMatchers("/api/v1/aparcamiento").hasRole("admin")
                .requestMatchers("/api/v1/aparcamiento/*").hasRole("admin")

                .anyRequest().authenticated()
            )
            .oauth2ResourceServer(oauth2 -> oauth2
                .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtConverter.jwtAuthenticationConverter()))
            );

        return http.build();
    }
}
