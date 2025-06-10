package com.uv.project.bike_data.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.uv.project.shared.domain.Evento;

@Repository
public interface EventoRepository extends MongoRepository<Evento, String> {
    
    List<Evento> findByIdAparcamiento(int idAparcamiento);

    List<Evento> findByFechaBetweenAndIdAparcamiento(LocalDate from, LocalDate to, int idAparcamiento);
}
