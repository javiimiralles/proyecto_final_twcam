package com.uv.project.pollution_service.controllers;



import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.uv.project.pollution_service.domain.Lectura;
import com.uv.project.pollution_service.services.LecturaService;

@RestController
public class LecturaController {

    @Autowired
    private LecturaService lecturaService;

    @PostMapping("/estacion/{id}")
    public ResponseEntity<Lectura> enviarLectura(@PathVariable String id, @RequestBody Lectura lectura) {
        lectura.setEstacionId(id);
        return ResponseEntity.status(201).body(lecturaService.guardarLectura(lectura));
    }

    @GetMapping("/estacion/{id}/status")
    public ResponseEntity<Lectura> ultimaLectura(@PathVariable String id) {
        List<Lectura> lecturas = lecturaService.getLecturasByEstacion(id);
        return lecturas.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(lecturas.get(0));
    }

    @GetMapping("/estacion/{id}/status-range")
    public ResponseEntity<List<Lectura>> lecturasPorRango(
        @PathVariable String id,
        @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant from,
        @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant to) {

        return ResponseEntity.ok(lecturaService.getLecturasByEstacionAndRango(id, from, to));
    }
}
