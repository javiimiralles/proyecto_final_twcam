package com.uv.project.bike_data.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

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
            .cors(cors -> cors.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    AntPathRequestMatcher.antMatcher("/api/v1/aparcamientos"),
                    AntPathRequestMatcher.antMatcher("/api/v1/aparcamientos/ranking"),
                    AntPathRequestMatcher.antMatcher("/api/v1/aparcamiento/**/status")
                ).permitAll()
                .requestMatchers(
                    AntPathRequestMatcher.antMatcher("/api/v1/aparcamiento"),
                    AntPathRequestMatcher.antMatcher("/api/v1/aparcamiento/**")
                ).hasAuthority("CLIENT_admin-client")
                .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.POST, "/api/v1/evento/**")).hasAuthority("CLIENT_aparcamiento-client")
                .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/api/v1/evento/**")).hasAuthority("CLIENT_servicio-client")
                .anyRequest().authenticated()
            );
            // .oauth2ResourceServer(oauth2 -> oauth2
            //     .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtConverter.jwtAuthenticationConverter()))
            // );
        return http.build();
    }
}
