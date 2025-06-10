package com.uv.project.pollution_data.controllers;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uv.project.pollution_data.services.LecturaService;
import com.uv.project.shared.domain.Lectura;

@RestController
@RequestMapping("/api/v1")
public class LecturaController {
    
    @Autowired
    private LecturaService lecturaService;

    @GetMapping("/estacion/{id}/status")
    List<Lectura> getLecturasByEstacion(@PathVariable int id) {
        return lecturaService.getLecturasByEstacion(id);
    }

    @GetMapping("/estacion/{id}/status/interval")
    List<Lectura> getLecturasByEstacionAndRango(
            @PathVariable int id,
            @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant from,
            @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant to) {
        return lecturaService.getLecturasByEstacionAndRango(id, from, to);
    }

    @PostMapping("/estacion/{id}")
    Lectura enviarLectura(@PathVariable int id, @RequestBody Lectura lectura) {
        lectura.setEstacionId(id);
        return lecturaService.guardarLectura(lectura);
    }
}
