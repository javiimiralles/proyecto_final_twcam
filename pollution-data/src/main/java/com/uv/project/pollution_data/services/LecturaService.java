package com.uv.project.pollution_data.services;

import java.time.Instant;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uv.project.pollution_data.repository.LecturaRepository;
import com.uv.project.shared.domain.Lectura;

@Service
public class LecturaService {

    @Autowired
    private LecturaRepository lecturaRepository;

    public Lectura guardarLectura(Lectura lectura) {
        lectura.setTimeStamp(Instant.now());
        return lecturaRepository.save(lectura);
    }

    public List<Lectura> getLecturasByEstacion(int id) {
        return lecturaRepository.findByEstacionIdOrderByTimeStampDesc(id);
    }

    public List<Lectura> getLecturasByEstacionAndRango(int id, Instant from, Instant to) {
        return lecturaRepository.findByEstacionIdAndTimeStampBetweenOrderByTimeStampAsc(id, from, to);
    }
}
