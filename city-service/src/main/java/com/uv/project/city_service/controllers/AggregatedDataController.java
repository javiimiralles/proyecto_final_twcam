package com.uv.project.city_service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uv.project.city_service.domain.AggregatedData;
import com.uv.project.city_service.services.AggregatedDataService;

@RestController
@RequestMapping("/api/v1")
public class AggregatedDataController {

    @Autowired
    private AggregatedDataService aggregatedDataService;

    @GetMapping("/aggregatedData")
    public ResponseEntity<AggregatedData> getLastAggregatedData() {
        AggregatedData data = aggregatedDataService.getLastAggregatedData();
        if (data == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(data);
    }

    @PreAuthorize("hasRole('servicio')")
    @GetMapping("/aggregateData")
    public ResponseEntity<AggregatedData> aggregateData() {
        return ResponseEntity.ok(aggregatedDataService.aggregateData());
    }
    
}
