package com.uv.project.city_service.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.uv.project.city_service.providers.RestTemplateProvider;
import com.uv.project.shared.domain.Evento;

@Service
public class EventoServiceClient {

    @Autowired
    private RestTemplateProvider restTemplateProvider;

    @Value("${bike-service.url}")
    private String baseUrl;
    
    @Value("${keycloak.servicio-token}")
    private String servicioToken;

    public List<Evento> findEventosPorAparcamiento(int aparcamientoId) {
        RestTemplate restTemplate = restTemplateProvider.withToken(servicioToken);
        ResponseEntity<Evento[]> response = restTemplate.getForEntity(
            baseUrl + "/evento/" + aparcamientoId,
            Evento[].class
        );
        if (response.getBody() == null) {
            return List.of();
        }
        return List.of(response.getBody());
    }
}
