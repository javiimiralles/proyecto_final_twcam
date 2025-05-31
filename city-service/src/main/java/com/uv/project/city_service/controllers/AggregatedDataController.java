package com.uv.project.city_service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uv.project.city_service.services.AggregatedDataService;

@RestController
@RequestMapping("/api/v1")
public class AggregatedDataController {

    @Autowired
    private AggregatedDataService aggregatedDataService;
    
}
