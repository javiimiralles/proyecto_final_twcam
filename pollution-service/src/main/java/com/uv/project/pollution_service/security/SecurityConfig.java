package com.uv.project.pollution_service.security;

import com.uv.project.shared.domain.JwtConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

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
                // Endpoints públicos
                .requestMatchers("/api/v1/aparcamiento/*/status").permitAll()
                .requestMatchers("/api/v1/aparcamiento/*/status/**").permitAll()
                .requestMatchers("/api/v1/estaciones").permitAll()

                // Endpoints de administración
                .requestMatchers(HttpMethod.POST, "/api/v1/estacion").hasRole("admin")
                .requestMatchers(HttpMethod.DELETE, "/api/v1/estacion/*").hasRole("admin")
                .requestMatchers(HttpMethod.PUT, "/api/v1/estacion/*").hasRole("admin")

                // Envío de medición por estación
                .requestMatchers(HttpMethod.POST, "/api/v1/estacion/*").hasRole("estacion")

                // Todo lo demás requiere autenticación
                .anyRequest().authenticated()
            )
            .oauth2ResourceServer(oauth2 -> oauth2
                .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtConverter.jwtAuthenticationConverter()))
            );

        return http.build();
    }
}