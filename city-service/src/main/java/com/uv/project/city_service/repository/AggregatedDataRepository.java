package com.uv.project.city_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.uv.project.city_service.domain.AggregatedData;

@Repository
public interface AggregatedDataRepository extends MongoRepository<AggregatedData, String>  {

    AggregatedData findTopByOrderByTimestampDesc();
    
}
