package com.uv.project.bike_service.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.uv.project.bike_service.security.FeignClientInterceptor;
import com.uv.project.shared.domain.Evento;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@FeignClient(name = "evento-data", url = "${service.bike-data.url}", configuration = FeignClientInterceptor.class)
public interface EventoDataClient {
    
    @GetMapping("/evento/{idAparcamiento}")
    List<Evento> findEventosByAparcamiento(@PathVariable int idAparcamiento);

    @PostMapping("/evento/{id}")
    Evento notificarEvento(@PathVariable int id, @RequestBody Evento evento);

}
