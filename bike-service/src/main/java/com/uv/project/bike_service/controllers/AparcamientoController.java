package com.uv.project.bike_service.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uv.project.bike_service.domain.Aparcamiento;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/v1")
public class AparcamientoController {

    @GetMapping("/aparcamientos")
    public ResponseEntity<List<Aparcamiento>> findAparcamientos() {
        // ToDo - Logica para obtener todos los aparcamientos
        return ResponseEntity.ok(List.of(new Aparcamiento()));
    }
    
    @GetMapping("/aparcamientos/ranking")
    public ResponseEntity<List<Aparcamiento>> findTop10Aparcamientos() {
        // ToDo - Lógica para obtener los 10 aparcamientos con mayor número de bicis
        return ResponseEntity.ok(List.of(new Aparcamiento()));
    }

    @GetMapping("/aparcamiento/{id}/status")
    public ResponseEntity<?> getStatus(@PathVariable int id, 
                                                        @RequestParam(required = false) String from, 
                                                        @RequestParam(required = false) String to) {
        if (from != null && to != null) {
            // ToDo - Lógica para obtener estado en rango
            return ResponseEntity.ok().build();
        } else {
            // ToDo - Lógica para estado actual
            return ResponseEntity.ok().build();
        }
    }
    
    @PostMapping("/aparcamiento")
    public ResponseEntity<Aparcamiento> createAparcamiento(@RequestBody Aparcamiento aparcamiento) {
        // ToDo - Logica para guardar el aparcamiento
        return ResponseEntity.ok(aparcamiento);
    }

    @PutMapping("/aparcamiento/{id}")
    public ResponseEntity<Aparcamiento> updateAparcamiento(@PathVariable int id, @RequestBody Aparcamiento aparcamiento) {
        // ToDo - Logica para actualizar el aparcamiento
        return ResponseEntity.ok(aparcamiento);
    }

    @DeleteMapping("/aparcamiento/{id}")
    public ResponseEntity<Void> deleteAparcamiento(@PathVariable int id) {
        // ToDo - Logica para eliminar el aparcamiento
        return ResponseEntity.noContent().build();
    }
}
