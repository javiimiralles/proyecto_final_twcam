package com.uv.project.city_service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.uv.project.city_service.utils.TokenUtils;
import com.uv.project.shared.domain.Estacion;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;

import com.uv.project.city_service.services.EstacionService;


@RestController
@RequestMapping("/api/v1")
public class EstacionController {

    @Autowired
    private EstacionService estacionService;

    @Autowired
    private TokenUtils tokenUtils;

    @PreAuthorize("hasRole('admin')")
    @SecurityRequirement(name = "Bearer Auth")
    @PostMapping("/estacion")
    public ResponseEntity<Estacion> createEstacion(HttpServletRequest request,@RequestBody Estacion estacion) {
        try {
            String token = tokenUtils.extractBearerToken(request);
            Estacion createdEstacion = estacionService.createEstacion(estacion,token);
            return ResponseEntity.ok(createdEstacion);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PreAuthorize("hasRole('admin')")
    @SecurityRequirement(name = "Bearer Auth")
    @DeleteMapping("/estacion/{id}")
    public ResponseEntity<Void> deleteEstacion(HttpServletRequest request,@PathVariable Integer id) {
        try {
             String token = tokenUtils.extractBearerToken(request);
            estacionService.deleteEstacion(id, token);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
