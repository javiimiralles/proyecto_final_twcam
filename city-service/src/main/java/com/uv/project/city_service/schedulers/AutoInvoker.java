package com.uv.project.city_service.schedulers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.uv.project.city_service.domain.AggregatedData;
import com.uv.project.city_service.providers.RestTemplateProvider;

@Component
public class AutoInvoker {

    @Autowired
    private RestTemplateProvider restTemplateProvider;
    
    @Value("${keycloak.servicio-token}")
    private String servicioToken;

    @Value("${city-service.url}")
    private String baseUrl;

    @Scheduled(fixedRate = 60000) // 60 seconds
    public void invokeAggregateData() {
        try {
            RestTemplate restTemplate = restTemplateProvider.withToken(servicioToken);
            ResponseEntity<AggregatedData> response = restTemplate.getForEntity(
                baseUrl + "/aggregateData",
                AggregatedData.class
            );

            System.out.println("Invocación automática exitosa: " + response.getBody());
        } catch (Exception e) {
            System.err.println("Error invocando /aggregateData: " + e.getMessage());
        }
    }
}
