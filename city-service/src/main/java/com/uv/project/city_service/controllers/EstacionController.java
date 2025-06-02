package com.uv.project.city_service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.uv.project.shared.domain.Estacion;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import com.uv.project.city_service.services.EstacionService;

@RestController
@RequestMapping("/api/v1")
public class EstacionController {

    @Autowired
    private EstacionService estacionService;

    @PreAuthorize("hasRole('admin')")
    @SecurityRequirement(name = "Bearer Auth")
    @PostMapping("/estacion")
    public ResponseEntity<Estacion> createEstacion(@RequestBody Estacion estacion) {
        try {
            Estacion created = estacionService.createEstacion(estacion);
            return ResponseEntity.ok(created);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PreAuthorize("hasRole('admin')")
    @SecurityRequirement(name = "Bearer Auth")
    @DeleteMapping("/estacion/{id}")
    public ResponseEntity<Void> deleteEstacion(@PathVariable Integer id) {
        try {
            estacionService.deleteEstacion(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
