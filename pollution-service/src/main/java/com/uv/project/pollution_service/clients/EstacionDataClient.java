package com.uv.project.pollution_service.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.uv.project.pollution_service.security.FeignClientInterceptor;
import com.uv.project.shared.domain.Estacion;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@FeignClient(name = "estacion-data", url = "${service.pollution-data.url}", configuration = FeignClientInterceptor.class)
public interface EstacionDataClient {
    
    @GetMapping("/estaciones")
    public List<Estacion> getEstaciones();
    
    @PostMapping("/estacion")
    public Estacion crearEstacion(@RequestBody Estacion estacion);

    @DeleteMapping("/estacion/{id}")
    public Estacion eliminarEstacion(@PathVariable int id);
}
