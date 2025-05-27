package com.uv.project.bike_service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uv.project.bike_service.domain.Evento;
import com.uv.project.bike_service.services.EventoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/v1/evento")
public class EventoController {

    @Autowired
    private EventoService eventoService;
    
    @PreAuthorize("hasRole('aparcamiento')")
    @PostMapping("/{id}")
    @SecurityRequirement(name = "Bearer Auth")
    @Operation(
        summary = "Notificar un evento",
        description = "Permite a un aparcamiento notificar un evento relacionado con el estado de las bicis."
    )
    public ResponseEntity<Evento> notificarEvento(@PathVariable int id, @RequestBody Evento evento) {
        Evento eventoCreado = eventoService.saveEvento(id, evento);
        if (eventoCreado == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(eventoCreado);
    }
    
}
