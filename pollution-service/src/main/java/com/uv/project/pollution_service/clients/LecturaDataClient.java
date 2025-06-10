package com.uv.project.pollution_service.clients;

import java.time.Instant;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.uv.project.pollution_service.security.FeignClientInterceptor;
import com.uv.project.shared.domain.Lectura;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@FeignClient(name = "lectura-data", url = "${service.pollution-data.url}", configuration = FeignClientInterceptor.class)
public interface LecturaDataClient {
    
    @GetMapping("/estacion/{id}/status")
    List<Lectura> getLecturasByEstacion(@PathVariable int id);

    @GetMapping("/estacion/{id}/status/interval")
    List<Lectura> getLecturasByEstacionAndRango(
            @PathVariable int id,
            @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant from,
            @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant to);

    @PostMapping("/estacion/{id}")
    Lectura enviarLectura(@PathVariable int id, @RequestBody Lectura lectura);

}
