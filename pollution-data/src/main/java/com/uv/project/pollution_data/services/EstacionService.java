package com.uv.project.pollution_data.services;
import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uv.project.pollution_data.repository.EstacionRepository;
import com.uv.project.shared.domain.Estacion;

@Service
public class EstacionService {

    @Autowired
    private EstacionRepository estacionRepository;

    public Estacion crearEstacion(Estacion estacion) {
        return estacionRepository.save(estacion);
    }

    public List<Estacion> getAllEstaciones() {
        return estacionRepository.findAll();
    }

    public Optional<Estacion> getEstacionById(int id) {
        return estacionRepository.findById(id);
    }

    public void eliminarEstacion(int id) {
        estacionRepository.deleteById(id);
    }

    public Estacion modificarEstacion(int id, Estacion nueva) {
        if (estacionRepository.existsById(id)) {
            nueva.setId(id);
            return estacionRepository.save(nueva);
        }
        return null;
    }
}
