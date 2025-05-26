package com.uv.project.bike_service.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uv.project.bike_service.domain.Aparcamiento;
import com.uv.project.bike_service.domain.Evento;
import com.uv.project.bike_service.objects.AparcamientoStatus;
import com.uv.project.bike_service.repository.AparcamientoRepository;
import com.uv.project.bike_service.repository.EventoRepository;

@Service
public class AparcamientoService {
    
    @Autowired
    private AparcamientoRepository aparcamientoRepository;

    @Autowired
    private EventoRepository eventoRepository;

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

    public AparcamientoStatus getStatus(int id) {
        logger.info("getStatus() - Obteniendo el estado del aparcamiento con ID: " + id);

        Aparcamiento aparcamiento = aparcamientoRepository.findById(id).orElse(null);
        if (aparcamiento == null) {
            logger.warning("getStatus() - No se encontró el aparcamiento con ID: " + id);
            return null;
        }

        AparcamientoStatus status = new AparcamientoStatus();
        status.setId(aparcamiento.getId());
        status.setHuecosDisponibles(aparcamiento.getCapacidad() - aparcamiento.getNumBicis());
        status.setBicisDisponibles(aparcamiento.getNumBicis());
        status.setFecha(LocalDateTime.now());
        return status;
    }

    public List<AparcamientoStatus> getStatus(int id, LocalDate from, LocalDate to) {
        logger.info("getStatus() - Obteniendo el estado del aparcamiento con ID: " + id + " desde " + from + " hasta " + to);
        
        List<Evento> eventos = eventoRepository.findByFechaBetweenAndIdAparcamiento(from, to, id);

        if (eventos == null || eventos.isEmpty()) {
            logger.warning("getStatus() - No se encontraron eventos para el aparcamiento con ID: " + id);
            return List.of();
        }

        return eventos.stream()
            .map(evento -> {
                AparcamientoStatus status = new AparcamientoStatus();
                status.setId(evento.getIdAparcamiento());
                status.setHuecosDisponibles(evento.getHuecosDisponibles());
                status.setBicisDisponibles(evento.getBicisDisponibles());
                status.setFecha(evento.getFecha());
                return status;
            })
            .toList();

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
