package com.uv.project.bike_service.bootstrap;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.uv.project.bike_service.domain.Evento;
import com.uv.project.bike_service.enums.OperacionEnum;
import com.uv.project.bike_service.repository.EventoRepository;

@Component
public class MongoDataLoader implements CommandLineRunner {

    @Autowired
    private EventoRepository eventoRepository;

    @Override
    public void run(String... args) throws Exception {
        eventoRepository.deleteAll();

        List<Evento> eventos = List.of(
            new Evento(null, 1, OperacionEnum.aparcamiento, 10, 5, LocalDateTime.now().minusDays(3).minusHours(4)),
            new Evento(null, 1, OperacionEnum.alquiler, 11, 4, LocalDateTime.now().minusDays(1).minusHours(9)),
            new Evento(null, 2, OperacionEnum.aparcamiento, 13, 2, LocalDateTime.now().minusDays(4).minusHours(7)),
            new Evento(null, 2, OperacionEnum.alquiler, 14, 1, LocalDateTime.now().minusDays(3).minusHours(1)),
            new Evento(null, 3, OperacionEnum.reposicion_multiple, 5, 10, LocalDateTime.now().minusDays(1).minusHours(9)),
            new Evento(null, 4, OperacionEnum.retirada_multiple, 10, 0, LocalDateTime.now().minusDays(5).minusHours(4)),
            new Evento(null, 5, OperacionEnum.aparcamiento, 12, 3, LocalDateTime.now().minusDays(6).minusHours(50)),
            new Evento(null, 5, OperacionEnum.alquiler, 13, 2, LocalDateTime.now().minusDays(45).minusHours(5))
        );

        eventoRepository.saveAll(eventos);
    }
    
}
