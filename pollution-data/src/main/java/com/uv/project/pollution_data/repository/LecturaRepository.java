package com.uv.project.pollution_data.repository;

import java.time.Instant;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.uv.project.shared.domain.Lectura;

public interface LecturaRepository extends MongoRepository<Lectura, String> {
    List<Lectura> findByEstacionIdOrderByTimeStampDesc(int estacionId);

    List<Lectura> findByEstacionIdAndTimeStampBetweenOrderByTimeStampAsc(
        int estacionId, Instant from, Instant to);
}
