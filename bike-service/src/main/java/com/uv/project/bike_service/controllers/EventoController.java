package com.uv.project.bike_service.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uv.project.bike_service.domain.Evento;

@RestController
@RequestMapping("/evento")
public class EventoController {
    
    @PostMapping("/{id}")
    public ResponseEntity<Evento> notificarEvento(@PathVariable int id, @RequestBody Evento evento) {
        // lógica para notificar evento
        return ResponseEntity.ok().build();
    }
    
}
