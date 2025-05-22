package com.uv.project.pollution_service.services;

import java.time.Instant;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uv.project.pollution_service.domain.Lectura;
import com.uv.project.pollution_service.repository.LecturaRepository;

@Service
public class LecturaService {

    @Autowired
    private LecturaRepository lecturaRepository;

    public Lectura guardarLectura(Lectura lectura) {
        lectura.setTimeStamp(Instant.now());
        return lecturaRepository.save(lectura);
    }

    public List<Lectura> getLecturasByEstacion(String id) {
        return lecturaRepository.findByEstacionIdOrderByTimeStampDesc(id);
    }

    public List<Lectura> getLecturasByEstacionAndRango(String id, Instant from, Instant to) {
        return lecturaRepository.findByEstacionIdAndTimeStampBetweenOrderByTimeStampAsc(id, from, to);
    }
}
