package com.uv.project.pollution_service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.uv.project.pollution_service.services.EstacionService;
import com.uv.project.shared.domain.Estacion;

@RestController
@RequestMapping("/api/v1")
public class EstacionController {

    @Autowired
    private EstacionService estacionService;

    @PostMapping("/estacion")
    public ResponseEntity<Estacion> crearEstacion(@RequestBody Estacion estacion) {
        return ResponseEntity.status(201).body(estacionService.crearEstacion(estacion));
    }

    @GetMapping("/estaciones")
    public ResponseEntity<List<Estacion>> getAllEstaciones() {
        List<Estacion> estaciones = estacionService.getAllEstaciones();
        return estaciones.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(estaciones);
    }

    @DeleteMapping("/estacion/{id}")
    public ResponseEntity<Void> eliminarEstacion(@PathVariable int id) {
        if (estacionService.getEstacionById(id).isPresent()) {
            estacionService.eliminarEstacion(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/estacion/{id}")
    public ResponseEntity<Estacion> modificarEstacion(@PathVariable int id, @RequestBody Estacion estacion) {
        Estacion modificada = estacionService.modificarEstacion(id, estacion);
        return modificada == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(modificada);
    }
}
