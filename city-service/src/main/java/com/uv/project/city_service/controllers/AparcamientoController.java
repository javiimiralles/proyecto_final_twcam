package com.uv.project.city_service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uv.project.city_service.client.BikeServiceClient;
import com.uv.project.city_service.utils.TokenUtils;
import com.uv.project.shared.domain.Aparcamiento;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1")
public class AparcamientoController {
    
    @Autowired
    private BikeServiceClient bikeServiceClient;

    @Autowired
    private TokenUtils tokenUtils;

    @GetMapping("/aparcamientoCercano")
    public ResponseEntity<Aparcamiento> getAparcamientoCercano(@RequestParam double lat, @RequestParam double lon) {
        List<Aparcamiento> aparcamientos = bikeServiceClient.findAparcamientos();
        if (aparcamientos == null || aparcamientos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Aparcamiento masCercano = aparcamientos.get(0);
        double minDist = distancia(lat, lon, masCercano.getLatitud(), masCercano.getLongitud());
        for (Aparcamiento a : aparcamientos) {
            double dist = distancia(lat, lon, a.getLatitud(), a.getLongitud());
            if (dist < minDist) {
                minDist = dist;
                masCercano = a;
            }
        }
        return ResponseEntity.ok(masCercano);
    }

    @PreAuthorize("hasRole('admin')")
    @SecurityRequirement(name = "Bearer Auth")
    @PostMapping("/aparcamiento")
    public ResponseEntity<Aparcamiento> createAparcamiento(HttpServletRequest request, @RequestBody Aparcamiento aparcamiento) {
        try {
            String token = tokenUtils.extractBearerToken(request);
            Aparcamiento createdAparcamiento = bikeServiceClient.createAparcamiento(aparcamiento, token);
            return ResponseEntity.ok(createdAparcamiento);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PreAuthorize("hasRole('admin')")
    @SecurityRequirement(name = "Bearer Auth")
    @PutMapping("/aparcamiento/{id}")
    public ResponseEntity<Aparcamiento> updateAparcamiento(HttpServletRequest request, @PathVariable int id, @RequestBody Aparcamiento aparcamiento) {
        try {
            String token = tokenUtils.extractBearerToken(request);
            Aparcamiento updatedAparcamiento = bikeServiceClient.updateAparcamiento(id, aparcamiento, token);
            return ResponseEntity.ok(updatedAparcamiento);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PreAuthorize("hasRole('admin')")
    @SecurityRequirement(name = "Bearer Auth")
    @DeleteMapping("/aparcamiento/{id}")
    public ResponseEntity<Void> deleteAparcamiento(HttpServletRequest request, @PathVariable int id) {
        try {
            String token = tokenUtils.extractBearerToken(request);
            bikeServiceClient.deleteAparcamiento(id, token);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private double distancia(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Radio de la Tierra en km
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }


}
