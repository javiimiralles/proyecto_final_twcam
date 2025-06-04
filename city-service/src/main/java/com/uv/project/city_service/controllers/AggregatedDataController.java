package com.uv.project.city_service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uv.project.city_service.services.AggregatedDataService;
import com.uv.project.shared.domain.AggregatedData;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v1")
public class AggregatedDataController {

    @Autowired
    private AggregatedDataService aggregatedDataService;

    @GetMapping("/aggregatedData")
    @Operation(summary = "Obtener el último dato agregado disponible", description = "Devuelve el último dato agregado disponible. (público)")
    public ResponseEntity<AggregatedData> getLastAggregatedData() {
        AggregatedData data = aggregatedDataService.getLastAggregatedData();
        if (data == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(data);
    }

    @PreAuthorize("hasAuthority('CLIENT_servicio-client')")
    @GetMapping("/aggregateData")
    @SecurityRequirement(name = "Bearer Auth")
    @Operation(summary = "Generar nuevos datos agregados", description = "Genera nuevos datos agregados a partir de las lecturas de las estaciones. (requiere rol servicio)")
    public ResponseEntity<AggregatedData> aggregateData() {
        return ResponseEntity.ok(aggregatedDataService.aggregateData());
    }
    
}
