package com.uv.project.bike_service.clients;

import java.time.LocalDate;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.uv.project.bike_service.security.FeignClientInterceptor;
import com.uv.project.shared.domain.Aparcamiento;
import com.uv.project.shared.domain.AparcamientoStatus;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@FeignClient(name = "aparcamiento-data", url = "${service.bike-data.url}", configuration = FeignClientInterceptor.class)
public interface AparcamientoDataClient {
    
    @GetMapping("/aparcamientos")
    List<Aparcamiento> findAparcamientos();

    @GetMapping("/aparcamientos/ranking")
    List<Aparcamiento> findTop10Aparcamientos();

    @GetMapping("/aparcamientos/{id}/status")
    AparcamientoStatus findAparcamientoStatus(@PathVariable int id);

    @GetMapping("/aparcamientos/{id}/status/interval")
    List<AparcamientoStatus> findAparcamientoStatusByDateRange(@PathVariable int id, @RequestParam LocalDate from, @RequestParam LocalDate to);

    @PostMapping("/aparcamiento")
    Aparcamiento createAparcamiento(@RequestBody Aparcamiento aparcamiento);

    @PutMapping("/aparcamiento/{id}")
    Aparcamiento updateAparcamiento(@PathVariable int id, @RequestBody Aparcamiento aparcamiento);

    @DeleteMapping("/aparcamiento/{id}")
    void deleteAparcamiento(@PathVariable int id);
}
