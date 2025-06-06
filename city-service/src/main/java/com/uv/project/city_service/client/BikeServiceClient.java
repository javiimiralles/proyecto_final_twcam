package com.uv.project.city_service.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.uv.project.city_service.providers.RestTemplateProvider;
import com.uv.project.shared.domain.Aparcamiento;

@Service
public class BikeServiceClient {

    @Autowired
    private RestTemplateProvider restTemplateProvider;
    
    @Value("${bike-service.url}")
    private String baseUrl;

    public List<Aparcamiento> findAparcamientos() {
        RestTemplate restTemplate = restTemplateProvider.withoutToken();
        ResponseEntity<Aparcamiento[]> response = restTemplate.getForEntity(
            baseUrl + "/aparcamientos",
            Aparcamiento[].class
        );
        return List.of(response.getBody());
    }

    public Aparcamiento createAparcamiento(Aparcamiento aparcamiento, String token) {
        RestTemplate restTemplate = restTemplateProvider.withToken(token);
        ResponseEntity<Aparcamiento> response = restTemplate.postForEntity(
            baseUrl + "/aparcamiento",
            aparcamiento,
            Aparcamiento.class
        );
        return response.getBody();
    }

    public Aparcamiento updateAparcamiento(int id, Aparcamiento aparcamiento, String token) {
        RestTemplate restTemplate = restTemplateProvider.withToken(token);
        aparcamiento.setId(id);
        restTemplate.put(
            baseUrl + "/aparcamiento/" + id,
            aparcamiento
        );
        return aparcamiento;
    }

    public void deleteAparcamiento(int id, String token) {
        RestTemplate restTemplate = restTemplateProvider.withToken(token);
        restTemplate.delete(baseUrl + "/aparcamiento/" + id);
    }
}
