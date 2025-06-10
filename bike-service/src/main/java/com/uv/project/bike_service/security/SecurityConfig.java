package com.uv.project.bike_service.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.uv.project.shared.domain.JwtConverter;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtConverter jwtConverter;
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .cors(cors -> cors.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    AntPathRequestMatcher.antMatcher("/api/v1/aparcamientos"),
                    AntPathRequestMatcher.antMatcher("/api/v1/aparcamientos/ranking"),
                    AntPathRequestMatcher.antMatcher("/api/v1/aparcamiento/**/status"),
                    AntPathRequestMatcher.antMatcher("/api/v1/api-spec"),
                    AntPathRequestMatcher.antMatcher("/api/v1/api-gui.html"),
                    AntPathRequestMatcher.antMatcher("/api/v1/api-gui.html/**"),
                    AntPathRequestMatcher.antMatcher("/v3/api-docs/**"),
                    AntPathRequestMatcher.antMatcher("/api/v1/swagger-ui/**"),
                    AntPathRequestMatcher.antMatcher("/api/v1/swagger-ui.html")
                ).permitAll()
                .requestMatchers(
                    AntPathRequestMatcher.antMatcher("/api/v1/aparcamiento"),
                    AntPathRequestMatcher.antMatcher("/api/v1/aparcamiento/**")
                ).hasAuthority("CLIENT_admin-client")
                .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.POST, "/api/v1/evento/**")).hasAuthority("CLIENT_aparcamiento-client")
                .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/api/v1/evento/**")).hasAuthority("CLIENT_servicio-client")
                .anyRequest().authenticated()
            )
            .oauth2ResourceServer(oauth2 -> oauth2
                .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtConverter.jwtAuthenticationConverter()))
            );

        return http.build();
    }

}
