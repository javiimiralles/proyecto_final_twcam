package com.uv.project.bike_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.uv.project.bike_service.domain.Evento;

public interface EventoRepository extends MongoRepository<Evento, String> {
}
