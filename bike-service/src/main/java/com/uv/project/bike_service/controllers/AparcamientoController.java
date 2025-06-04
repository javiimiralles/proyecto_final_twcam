package com.uv.project.bike_service.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uv.project.bike_service.objects.AparcamientoStatus;
import com.uv.project.bike_service.services.AparcamientoService;
import com.uv.project.shared.domain.Aparcamiento;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/v1")
public class AparcamientoController {

    @Autowired
    private AparcamientoService aparcamientoService;

    @GetMapping("/aparcamientos")
    @Operation(summary = "Obtener todos los aparcamientos", description = "Devuelve una lista de todos los aparcamientos disponibles. (público)")
    public ResponseEntity<List<Aparcamiento>> findAparcamientos() {
        List<Aparcamiento> aparcamientos = aparcamientoService.findAparcamientos();
        if (aparcamientos == null || aparcamientos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(aparcamientos);
    }
    
    @GetMapping("/aparcamientos/ranking")
    @Operation(summary = "Obtener los 10 aparcamientos con mayor número de bicis disponibles", description = "Devuelve una lista de los 10 aparcamientos con mayor número de bicis disponibles en ese momento. (público)")
    public ResponseEntity<List<Aparcamiento>> findTop10Aparcamientos() {
        List<Aparcamiento> aparcamientos = aparcamientoService.findTop10Aparcamientos();
        if (aparcamientos == null || aparcamientos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(aparcamientos);
    }

    @GetMapping("/aparcamiento/{id}/status")
    @Operation(
        summary = "Obtener el estado de un aparcamiento", 
        description = "Devuelve el estado actual de un aparcamiento específico. Si se proporcionan fechas, devuelve el estado en ese rango de fechas. (público)"
    )
    public ResponseEntity<?> getStatus(@PathVariable int id, 
                                        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from, 
                                        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        if (from != null && to != null) {
            List<AparcamientoStatus> statusList = aparcamientoService.getStatus(id, from, to);
            if (statusList == null || statusList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No se encontraron estados para el aparcamiento con ID: " + id + " en el rango de fechas especificado.");
            }
            return ResponseEntity.ok(statusList);
        } else {
            AparcamientoStatus status = aparcamientoService.getStatus(id);
            if (status == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aparcamiento con ID: " + id + " no encontrado.");
            }
            return ResponseEntity.ok(status);
        }
    }
    
    @PreAuthorize("hasAuthority('CLIENT_admin-client')")
    @PostMapping("/aparcamiento")
    @SecurityRequirement(name = "Bearer Auth")
    @Operation(summary = "Crear un nuevo aparcamiento", description = "Crea un nuevo aparcamiento con los detalles proporcionados. (requiere rol admin)")
    public ResponseEntity<Aparcamiento> createAparcamiento(@RequestBody Aparcamiento aparcamiento) {
        return ResponseEntity.status(HttpStatus.CREATED).body(aparcamientoService.createAparcamiento(aparcamiento));
    }

    @PreAuthorize("hasAuthority('CLIENT_admin-client')")
    @PutMapping("/aparcamiento/{id}")
    @SecurityRequirement(name = "Bearer Auth")
    @Operation(summary = "Actualizar un aparcamiento", description = "Actualiza los detalles de un aparcamiento existente con el ID proporcionado. (requiere rol admin)")
    public ResponseEntity<Aparcamiento> updateAparcamiento(@PathVariable int id, @RequestBody Aparcamiento aparcamiento) {
        if (aparcamientoService.findAparcamientoById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(aparcamientoService.updateAparcamiento(id, aparcamiento));
    }

    @PreAuthorize("hasAuthority('CLIENT_admin-client')")
    @DeleteMapping("/aparcamiento/{id}")
    @SecurityRequirement(name = "Bearer Auth")
    @Operation(summary = "Eliminar un aparcamiento", description = "Elimina un aparcamiento existente con el ID proporcionado. (requiere rol admin)")
    public ResponseEntity<Void> deleteAparcamiento(@PathVariable int id) {
        if (aparcamientoService.findAparcamientoById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        aparcamientoService.deleteAparcamiento(id);
        return ResponseEntity.ok().build();
    }
}
