package com.uv.project.bike_data.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uv.project.bike_data.services.AparcamientoService;
import com.uv.project.shared.domain.Aparcamiento;
import com.uv.project.shared.domain.AparcamientoStatus;

@RestController
@RequestMapping("/api/v1")
public class AparcamientoController {

    @Autowired
    private AparcamientoService aparcamientoService;

    @GetMapping("/aparcamientos")
    public List<Aparcamiento> findAparcamientos() {
        return aparcamientoService.findAparcamientos();
    }

    @GetMapping("/aparcamientos/ranking")
    public List<Aparcamiento> findTop10Aparcamientos() {
        return aparcamientoService.findTop10Aparcamientos();
    }

    @GetMapping("/aparcamientos/{id}/status")
    public AparcamientoStatus findAparcamientoStatus(@PathVariable int id) {
        return aparcamientoService.getStatus(id);
    }

    @GetMapping("/aparcamientos/{id}/status/interval")
    public List<AparcamientoStatus> findAparcamientoStatusWithDates(@PathVariable int id, 
                                        @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from, 
                                        @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        return aparcamientoService.getStatus(id, from, to);
    }

    @PostMapping("/aparcamiento")
    public Aparcamiento createAparcamiento(@RequestBody Aparcamiento aparcamiento) {
        return aparcamientoService.createAparcamiento(aparcamiento);
    }

    @PutMapping("/aparcamiento/{id}")
    public Aparcamiento updateAparcamiento(@PathVariable int id, @RequestBody Aparcamiento aparcamiento) {
        return aparcamientoService.updateAparcamiento(id, aparcamiento);
    }

    @DeleteMapping("/aparcamiento/{id}")
    public void deleteAparcamiento(@PathVariable int id) {
        aparcamientoService.deleteAparcamiento(id);
    }
    
}
