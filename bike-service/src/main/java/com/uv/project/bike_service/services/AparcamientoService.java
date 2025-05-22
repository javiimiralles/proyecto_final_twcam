package com.uv.project.bike_service.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uv.project.bike_service.domain.Aparcamiento;
import com.uv.project.bike_service.repository.AparcamientoRepository;

@Service
public class AparcamientoService {
    
    @Autowired
    private AparcamientoRepository aparcamientoRepository;

    Logger logger = Logger.getLogger(AparcamientoService.class.getName());

    public List<Aparcamiento> findAparcamientos() {
        logger.info("findAparcamientos() - Obteniendo todos los aparcamientos");
        return aparcamientoRepository.findAll();
    }

    public Aparcamiento findAparcamientoById(int id) {
        logger.info("findAparcamientoById() - Obteniendo el aparcamiento con ID: " + id);
        return aparcamientoRepository.findById(id).orElse(null);
    }

    public List<Aparcamiento> findTop10Aparcamientos() {
        logger.info("findTop10Aparcamientos() - Obteniendo los 10 aparcamientos con mayor número de bicis");
        return aparcamientoRepository.findTop10ByOrderByNumBicisDesc();
    }

    public Aparcamiento createAparcamiento(Aparcamiento aparcamiento) {
        logger.info("createAparcamiento() - Creando un nuevo aparcamiento");
        return aparcamientoRepository.save(aparcamiento);
    }

    public Aparcamiento updateAparcamiento(int id, Aparcamiento aparcamiento) {
        logger.info("updateAparcamiento() - Actualizando el aparcamiento con ID: " + id);
        aparcamiento.setId(id);
        return aparcamientoRepository.save(aparcamiento);
    }

    public void deleteAparcamiento(int id) {
        logger.info("deleteAparcamiento() - Eliminando el aparcamiento con ID: " + id);
        aparcamientoRepository.deleteById(id);
    }


}
