package com.uv.project.city_service.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.uv.project.shared.domain.Aparcamiento;

@Service
public class BikeServiceClient {
    
    @Value("${bike-service.url}")
    private String baseUrl;

    private final RestTemplate restTemplate;

    public BikeServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Aparcamiento createAparcamiento(Aparcamiento aparcamiento) {
        ResponseEntity<Aparcamiento> response = restTemplate.postForEntity(
            baseUrl + "/aparcamiento",
            aparcamiento,
            Aparcamiento.class
        );
        return response.getBody();
    }

    public Aparcamiento updateAparcamiento(Aparcamiento aparcamiento) {
        restTemplate.put(
            baseUrl + "/aparcamiento/" + aparcamiento.getId(),
            aparcamiento
        );
        return aparcamiento;
    }

    public void deleteAparcamiento(int id) {
        restTemplate.delete(baseUrl + "/aparcamiento/" + id);
    }
}
