package com.uv.project.pollution_service.repository;



import java.time.Instant;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.uv.project.pollution_service.domain.Lectura;

public interface LecturaRepository extends MongoRepository<Lectura, String> {
    List<Lectura> findByEstacionIdOrderByTimeStampDesc(String estacionId);

    List<Lectura> findByEstacionIdAndTimeStampBetweenOrderByTimeStampAsc(
        String estacionId, Instant from, Instant to);
}
