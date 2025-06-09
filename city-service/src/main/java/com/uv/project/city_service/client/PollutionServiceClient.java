package com.uv.project.city_service.client;

import com.uv.project.city_service.providers.RestTemplateProvider;
import com.uv.project.shared.domain.Estacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PollutionServiceClient {

    @Autowired
    private RestTemplateProvider restTemplateProvider;

    @Value("${pollution-service.url}")
    private String baseUrl;
    
    public List<Estacion> findEstaciones() {
        RestTemplate restTemplate = restTemplateProvider.withoutToken();
        ResponseEntity<Estacion[]> response = restTemplate.getForEntity(
            baseUrl + "/estaciones",
            Estacion[].class
        );
        return List.of(response.getBody());
    }

    public Estacion createEstacion(Estacion estacion, String token) {
        RestTemplate restTemplate = restTemplateProvider.withToken(token);
        ResponseEntity<Estacion> response = restTemplate.postForEntity(
            baseUrl + "/estacion",
            estacion,
            Estacion.class
        );
        return response.getBody();
    }

    public Estacion updateEstacion(int id, Estacion estacion, String token) {
        RestTemplate restTemplate = restTemplateProvider.withToken(token);
        estacion.setId(id); 
        restTemplate.put(
            baseUrl + "/estacion/" + id,
            estacion
        );
        return estacion;
    }

    public void deleteEstacion(int id, String token) {
        RestTemplate restTemplate = restTemplateProvider.withToken(token);
        restTemplate.delete(baseUrl + "/estacion/" + id);
    }
}
