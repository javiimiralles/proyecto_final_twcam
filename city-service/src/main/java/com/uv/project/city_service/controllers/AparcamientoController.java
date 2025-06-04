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
import com.uv.project.city_service.utils.DistanceUtils;
import com.uv.project.city_service.utils.TokenUtils;
import com.uv.project.shared.domain.Aparcamiento;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1")
public class AparcamientoController {
    
    @Autowired
    private BikeServiceClient bikeServiceClient;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private DistanceUtils distanceUtils;

    @GetMapping("/aparcamientoCercano")
    @Operation(summary = "Obtener el aparcamiento más cercano según latitud y longitud", description = "Devuelve el aparcamiento más cercano a las coordenadas proporcionadas. (público)")
    public ResponseEntity<Aparcamiento> getAparcamientoCercano(@RequestParam double lat, @RequestParam double lon) {
        List<Aparcamiento> aparcamientos = bikeServiceClient.findAparcamientos();
        if (aparcamientos == null || aparcamientos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Aparcamiento masCercano = aparcamientos.get(0);
        double minDist = distanceUtils.calcularDistancia(lat, lon, masCercano.getLatitud(), masCercano.getLongitud());
        for (Aparcamiento a : aparcamientos) {
            double dist = distanceUtils.calcularDistancia(lat, lon, a.getLatitud(), a.getLongitud());
            if (dist < minDist) {
                minDist = dist;
                masCercano = a;
            }
        }
        return ResponseEntity.ok(masCercano);
    }

    @PreAuthorize("hasAuthority('CLIENT_admin-client')")
    @PostMapping("/aparcamiento")
    @SecurityRequirement(name = "Bearer Auth")
    @Operation(summary = "Crear un nuevo aparcamiento", description = "Permite a un usuario con rol admin crear un nuevo aparcamiento. (requiere rol admin)")
    public ResponseEntity<Aparcamiento> createAparcamiento(HttpServletRequest request, @RequestBody Aparcamiento aparcamiento) {
        try {
            String token = tokenUtils.extractBearerToken(request);
            Aparcamiento createdAparcamiento = bikeServiceClient.createAparcamiento(aparcamiento, token);
            return ResponseEntity.ok(createdAparcamiento);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PreAuthorize("hasAuthority('CLIENT_admin-client')")
    @PutMapping("/aparcamiento/{id}")
    @SecurityRequirement(name = "Bearer Auth")
    @Operation(summary = "Actualizar un aparcamiento existente por ID", description = "Permite a un usuario con rol admin actualizar un aparcamiento existente por su ID. (requiere rol admin)")
    public ResponseEntity<Aparcamiento> updateAparcamiento(HttpServletRequest request, @PathVariable int id, @RequestBody Aparcamiento aparcamiento) {
        try {
            String token = tokenUtils.extractBearerToken(request);
            Aparcamiento updatedAparcamiento = bikeServiceClient.updateAparcamiento(id, aparcamiento, token);
            return ResponseEntity.ok(updatedAparcamiento);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PreAuthorize("hasAuthority('CLIENT_admin-client')")
    @DeleteMapping("/aparcamiento/{id}")
    @SecurityRequirement(name = "Bearer Auth")
    @Operation(summary = "Eliminar un aparcamiento por ID", description = "Permite a un usuario con rol admin eliminar un aparcamiento por su ID. (requiere rol admin)")
    public ResponseEntity<Void> deleteAparcamiento(HttpServletRequest request, @PathVariable int id) {
        try {
            String token = tokenUtils.extractBearerToken(request);
            bikeServiceClient.deleteAparcamiento(id, token);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
