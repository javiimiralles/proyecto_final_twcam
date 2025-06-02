package com.uv.project.pollution_service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.uv.project.pollution_service.services.EstacionService;
import com.uv.project.shared.domain.Estacion;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/v1")
public class EstacionController {

    @Autowired
    private EstacionService estacionService;

    @Operation(summary = "Crear una nueva estación de medición (requiere rol admin)")
    @PreAuthorize("hasRole('admin')")
    @SecurityRequirement(name = "Bearer Auth")
    @PostMapping("/estacion")
    public ResponseEntity<Estacion> crearEstacion(@RequestBody Estacion estacion) {
        return ResponseEntity.status(201).body(estacionService.crearEstacion(estacion));
    }

    @Operation(summary = "Obtener el listado de todas las estaciones")
    @GetMapping("/estaciones")
    public ResponseEntity<List<Estacion>> getAllEstaciones() {
        List<Estacion> estaciones = estacionService.getAllEstaciones();
        return estaciones.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(estaciones);
    }

    @Operation(summary = "Eliminar una estación por ID (requiere rol admin)")
    @PreAuthorize("hasRole('admin')")
    @SecurityRequirement(name = "Bearer Auth")
    @DeleteMapping("/estacion/{id}")
    public ResponseEntity<Void> eliminarEstacion(@PathVariable int id) {
        if (estacionService.getEstacionById(id).isPresent()) {
            estacionService.eliminarEstacion(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    // @Operation(summary = "Modificar una estación existente por ID (requiere rol admin)")
    // @PutMapping("/estacion/{id}")
    // public ResponseEntity<Estacion> modificarEstacion(@PathVariable int id, @RequestBody Estacion estacion) {
    //     Estacion modificada = estacionService.modificarEstacion(id, estacion);
    //     return modificada == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(modificada);
    // }
}
