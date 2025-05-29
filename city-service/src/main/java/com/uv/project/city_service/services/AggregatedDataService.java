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

    public AggregatedData saveAggregatedData(AggregatedData aggregatedData) {
        logger.info("saveAggregatedData() - Saving aggregated data: " + aggregatedData);

        
        return aggregatedDataRepository.save(aggregatedData);
    }
}
