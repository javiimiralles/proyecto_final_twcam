package com.uv.project.city_service.services;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uv.project.city_service.domain.AggregatedData;
import com.uv.project.city_service.repository.AggregatedDataRepository;

@Service
public class AggregatedDataService {
    
    @Autowired
    private AggregatedDataRepository aggregatedDataRepository;

    private Logger logger = Logger.getLogger(AggregatedDataService.class.getName());

    public AggregatedData getLastAggregatedData() {
        logger.info("Obteniendo los datos agregados más recientes");
        return aggregatedDataRepository.findTopByOrderByTimestampDesc();
    }

    public AggregatedData aggregateData() {
        logger.info("Saving aggregated data");
        AggregatedData aggregatedData = new AggregatedData();
        
        return aggregatedDataRepository.save(aggregatedData);
    }
}
