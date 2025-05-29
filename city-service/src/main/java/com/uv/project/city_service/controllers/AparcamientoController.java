package com.uv.project.city_service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uv.project.city_service.client.BikeServiceClient;
import com.uv.project.shared.domain.Aparcamiento;

@RestController
@RequestMapping("/api/v1")
public class AparcamientoController {
    
    @Autowired
    private BikeServiceClient bikeServiceClient;

    @GetMapping("/aparcamientoCercano")
    public ResponseEntity<Aparcamiento> getAparcamientoCercano() {
        Aparcamiento aparcamiento = bikeServiceClient.createAparcamiento(new Aparcamiento());
        return ResponseEntity.ok(aparcamiento);
    }
}
