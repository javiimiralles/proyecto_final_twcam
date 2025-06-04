package com.uv.project.pollution_service.security;

import com.uv.project.shared.domain.JwtConverter;
import org.springframework.http.HttpMethod;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
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
            // Swagger
            .requestMatchers(
                AntPathRequestMatcher.antMatcher("/api/v1/api-spec"),
                AntPathRequestMatcher.antMatcher("/api/v1/api-gui.html"),
                AntPathRequestMatcher.antMatcher("/api/v1/api-gui.html/**"),
                AntPathRequestMatcher.antMatcher("/v3/api-docs/**"),
                AntPathRequestMatcher.antMatcher("/api/v1/swagger-ui/**"),
                AntPathRequestMatcher.antMatcher("/api/v1/swagger-ui.html")
            ).permitAll()

            // Endpoints públicos
            .requestMatchers(
                AntPathRequestMatcher.antMatcher("/api/v1/aparcamiento/*/status"),
                AntPathRequestMatcher.antMatcher("/api/v1/aparcamiento/*/status/**"),
                AntPathRequestMatcher.antMatcher("/api/v1/estaciones"),
                AntPathRequestMatcher.antMatcher("/api/v1/estacion/*/status"),
                AntPathRequestMatcher.antMatcher("/api/v1/estacion/*/status/**")
            ).permitAll()

         
            .requestMatchers(HttpMethod.POST, "/api/v1/estacion/*").hasAuthority("CLIENT_estacion-client")
            .requestMatchers(HttpMethod.DELETE, "/api/v1/estacion/*").hasAuthority("CLIENT_admin-client")
            .requestMatchers(HttpMethod.POST, "/api/v1/estacion").hasAuthority("CLIENT_admin-client")
            .requestMatchers(HttpMethod.GET, "/api/v1/estacion/**").authenticated()

            .anyRequest().authenticated()
        )
        .oauth2ResourceServer(oauth2 -> oauth2
            .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtConverter.jwtAuthenticationConverter()))
        );

    return http.build();
}


}
