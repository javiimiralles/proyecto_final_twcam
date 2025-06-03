package com.uv.project.city_service.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.uv.project.city_service.providers.RestTemplateProvider;
import com.uv.project.shared.domain.Lectura;

@Service
public class LecturaServiceClient {

    @Autowired
    private RestTemplateProvider restTemplateProvider;
    
    @Value("${pollution-service.url}")
    private String baseUrl;

    public List<Lectura> findLecturasPorEstacion(int estacionId) {
        RestTemplate restTemplate = restTemplateProvider.withoutToken();
        ResponseEntity<Lectura[]> response = restTemplate.getForEntity(
            baseUrl + "/estacion/" + estacionId + "/status?from=1970-01-01T00:00:00Z&to=9999-12-31T23:59:59Z",
            Lectura[].class
        );
        if (response.getBody() == null) {
            return List.of();
        }
        return List.of(response.getBody());
    }
}
