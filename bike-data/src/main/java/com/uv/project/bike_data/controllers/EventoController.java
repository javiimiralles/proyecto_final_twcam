package com.uv.project.bike_data.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uv.project.bike_data.services.EventoService;
import com.uv.project.shared.domain.Evento;

@RestController
@RequestMapping("/api/v1/evento")
public class EventoController {
    
    @Autowired
    private EventoService eventoService;

    @GetMapping("/{idAparcamiento}")
    public List<Evento> findEventosByAparcamiento(@PathVariable int idAparcamiento) {
        return eventoService.findEventosByAparcamiento(idAparcamiento);
    }

    @PostMapping("/{id}")
    public Evento notificarEvento(@PathVariable int id, @RequestBody Evento evento) {
        return eventoService.saveEvento(id, evento);
    }
}
