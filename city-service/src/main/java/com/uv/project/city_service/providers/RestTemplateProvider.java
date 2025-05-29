package com.uv.project.city_service.providers;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class RestTemplateProvider  {

    private final RestTemplateBuilder restTemplateBuilder;

    public RestTemplateProvider(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public RestTemplate withToken(String token) {
        return restTemplateBuilder
                .defaultHeader("Authorization", "Bearer " + token)
                .build();
    }
}
