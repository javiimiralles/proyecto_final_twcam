package com.uv.project.bike_data.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uv.project.bike_data.repository.AparcamientoRepository;
import com.uv.project.bike_data.repository.EventoRepository;
import com.uv.project.shared.domain.Aparcamiento;
import com.uv.project.shared.domain.Evento;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private AparcamientoRepository aparcamientoRepository;

    private Logger logger = Logger.getLogger(EventoService.class.getName());

    public List<Evento> findEventosByAparcamiento(int idAparcamiento) {
        logger.info("findEventosByAparcamiento() - Buscando eventos para el aparcamiento con ID: " + idAparcamiento);
        return eventoRepository.findByIdAparcamiento(idAparcamiento);
    }

    @Transactional
    public Evento saveEvento(int idAparcamiento, Evento evento)  {
        logger.info("saveEvento() - Guardando evento: " + evento);

        Aparcamiento aparcamiento = aparcamientoRepository.findById(idAparcamiento).orElse(null);
        if (aparcamiento == null) {
            return null;
        }

        aparcamiento.setNumBicis(evento.getBicisDisponibles());
        aparcamientoRepository.save(aparcamiento);

        evento.setIdAparcamiento(idAparcamiento);
        evento.setFecha(LocalDateTime.now());
        return eventoRepository.save(evento);
    }

    
}
