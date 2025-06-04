package com.uv.project.pollution_service.controllers;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.uv.project.pollution_service.services.LecturaService;
import com.uv.project.shared.domain.Lectura;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/v1")
public class LecturaController {

    @Autowired
    private LecturaService lecturaService;

    @PreAuthorize("hasAuthority('CLIENT_estacion-client')")
    @PostMapping("/estacion/{id}")
    @SecurityRequirement(name = "Bearer Auth")
    @Operation(summary = "Enviar una lectura desde una estación", description = "Permite a una estación enviar una lectura de datos. (requiere rol estacion)")
    public ResponseEntity<Lectura> enviarLectura(@PathVariable int id, @RequestBody Lectura lectura) {
        lectura.setEstacionId(id);
        return ResponseEntity.status(201).body(lecturaService.guardarLectura(lectura));
    }

    // @Operation(summary = "Obtener la última lectura enviada por una estación (público)")
    // @GetMapping("/estacion/{id}/status")
    // public ResponseEntity<Lectura> ultimaLectura(@PathVariable String id) {
    //     List<Lectura> lecturas = lecturaService.getLecturasByEstacion(id);
    //     return lecturas.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(lecturas.get(0));
    // }

    @GetMapping("/estacion/{id}/status")
    @Operation(summary = "Consultar la última lectura o las lecturas por intervalo", description = "Permite consultar la última lectura de una estación o las lecturas en un intervalo de tiempo. (público)")
    public ResponseEntity<?> obtenerLecturas(
            @PathVariable int id,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant to) {

        if (from != null && to != null) {
            // Devolver lecturas en intervalo
            List<Lectura> lecturas = lecturaService.getLecturasByEstacionAndRango(id, from, to);
            return ResponseEntity.ok(lecturas);
        } else {
            // Devolver la última lectura
            List<Lectura> lecturas = lecturaService.getLecturasByEstacion(id);
            return lecturas.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(lecturas.get(0));
        }
    }

}
