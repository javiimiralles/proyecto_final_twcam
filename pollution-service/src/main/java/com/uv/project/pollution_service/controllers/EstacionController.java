package com.uv.project.pollution_service.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.uv.project.pollution_service.clients.EstacionDataClient;
import com.uv.project.shared.domain.Estacion;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/v1")
public class EstacionController {

    private final EstacionDataClient estacionDataClient;

    public EstacionController(EstacionDataClient estacionDataClient) {
        this.estacionDataClient = estacionDataClient;
    }

    @PreAuthorize("hasAuthority('CLIENT_admin-client')")
    @PostMapping("/estacion")
    @SecurityRequirement(name = "Bearer Auth")
    @Operation(summary = "Crear una nueva estación de medición", description = "Permite a un usuario con rol admin crear una nueva estación de medición. (requiere rol admin)")
    public ResponseEntity<Estacion> crearEstacion(@RequestBody Estacion estacion) {
        return ResponseEntity.status(201).body(estacionDataClient.crearEstacion(estacion));
    }

    @GetMapping("/estaciones")
    @Operation(summary = "Obtener el listado de todas las estaciones", description = "Devuelve una lista de todas las estaciones de medición disponibles. (público)")
    public ResponseEntity<List<Estacion>> getAllEstaciones() {
        List<Estacion> estaciones = estacionDataClient.getEstaciones();
        return estaciones.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(estaciones);
    }

    @PreAuthorize("hasAuthority('CLIENT_admin-client')")
    @DeleteMapping("/estacion/{id}")
    @SecurityRequirement(name = "Bearer Auth")
    @Operation(summary = "Eliminar una estación por ID", description = "Permite a un usuario con rol admin eliminar una estación de medición por su ID. (requiere rol admin)")
    public ResponseEntity<Void> eliminarEstacion(@PathVariable int id) {
        estacionDataClient.eliminarEstacion(id);
        return ResponseEntity.ok().build();
    }

    // @Operation(summary = "Modificar una estación existente por ID (requiere rol admin)")
    // @PutMapping("/estacion/{id}")
    // public ResponseEntity<Estacion> modificarEstacion(@PathVariable int id, @RequestBody Estacion estacion) {
    //     Estacion modificada = estacionService.modificarEstacion(id, estacion);
    //     return modificada == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(modificada);
    // }
}
