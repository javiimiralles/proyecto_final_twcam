package com.uv.project.bike_service.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uv.project.bike_service.clients.EventoDataClient;
import com.uv.project.shared.domain.Evento;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/v1/evento")
public class EventoController {

    private final EventoDataClient eventoDataClient;

    public EventoController(EventoDataClient eventoDataClient) {
        this.eventoDataClient = eventoDataClient;
    }

    @PreAuthorize("hasAuthority('CLIENT_servicio-client')")
    @GetMapping("/{idAparcamiento}")
    @SecurityRequirement(name = "Bearer Auth")
    @Operation(
        summary = "Obtener los eventos de un aparcamiento",
        description = "Permite obtener los eventos relacionados con un aparcamiento específico. (requiere rol servicio)"
    )
    public ResponseEntity<List<Evento>> findEventosByAparcamiento(@PathVariable int idAparcamiento) {
        List<Evento> eventos = eventoDataClient.findEventosByAparcamiento(idAparcamiento);
        if (eventos == null || eventos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(eventos);
    }
    
    @PreAuthorize("hasAuthority('CLIENT_aparcamiento-client')")
    @PostMapping("/{id}")
    @SecurityRequirement(name = "Bearer Auth")
    @Operation(
        summary = "Notificar un evento",
        description = "Permite a un aparcamiento notificar un evento relacionado con el estado de las bicis. (requiere rol aparcamiento)"
    )
    public ResponseEntity<Evento> notificarEvento(@PathVariable int id, @RequestBody Evento evento) {
        Evento eventoCreado = eventoDataClient.notificarEvento(id, evento);
        if (eventoCreado == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(eventoCreado);
    }
    
}
