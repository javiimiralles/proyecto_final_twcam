package com.uv.project.bike_service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.uv.project.bike_service.services.AparcamientoService;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/v1")
public class AparcamientoController {

    @Autowired
    private AparcamientoService aparcamientoService;

    @GetMapping("/aparcamientos")
    public ResponseEntity<List<Aparcamiento>> findAparcamientos() {
        List<Aparcamiento> aparcamientos = aparcamientoService.findAparcamientos();
        if (aparcamientos == null || aparcamientos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(aparcamientos);
    }
    
    @GetMapping("/aparcamientos/ranking")
    public ResponseEntity<List<Aparcamiento>> findTop10Aparcamientos() {
        List<Aparcamiento> aparcamientos = aparcamientoService.findTop10Aparcamientos();
        if (aparcamientos == null || aparcamientos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(aparcamientos);
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
        return ResponseEntity.status(HttpStatus.CREATED).body(aparcamientoService.createAparcamiento(aparcamiento));
    }

    @PutMapping("/aparcamiento/{id}")
    public ResponseEntity<Aparcamiento> updateAparcamiento(@PathVariable int id, @RequestBody Aparcamiento aparcamiento) {
        if (aparcamientoService.findAparcamientoById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(aparcamientoService.updateAparcamiento(id, aparcamiento));
    }

    @DeleteMapping("/aparcamiento/{id}")
    public ResponseEntity<Void> deleteAparcamiento(@PathVariable int id) {
        if (aparcamientoService.findAparcamientoById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        aparcamientoService.deleteAparcamiento(id);
        return ResponseEntity.ok().build();
    }
}
