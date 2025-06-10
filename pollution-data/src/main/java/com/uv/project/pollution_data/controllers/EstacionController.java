package com.uv.project.pollution_data.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uv.project.pollution_data.services.EstacionService;
import com.uv.project.shared.domain.Estacion;

@RestController
@RequestMapping("/api/v1")
public class EstacionController {
    
    @Autowired
    private EstacionService estacionService;

    @GetMapping("/estaciones")
    public List<Estacion> getAllEstaciones() {
        return estacionService.getAllEstaciones();
    }

    @PostMapping("/estacion")
    public Estacion crearEstacion(@RequestBody Estacion estacion) {
        return estacionService.crearEstacion(estacion);
    }

    @DeleteMapping("/estacion/{id}")
    public void eliminarEstacion(@PathVariable int id) {
        estacionService.eliminarEstacion(id);
    }
}
