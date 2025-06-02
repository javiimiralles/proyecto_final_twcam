package com.uv.project.pollution_service.bootstrap;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.uv.project.pollution_service.repository.LecturaRepository;
import com.uv.project.shared.domain.Lectura;

@Component
public class MongoDataLoaderLectura implements CommandLineRunner {

    @Autowired
    private LecturaRepository lecturaRepository;

    @Override
    public void run(String... args) throws Exception {
        lecturaRepository.deleteAll();

        List<Lectura> lecturas = List.of(
            crearLectura("EST001", 10.5, 5.2, 6.8, 12.1, 2),
            crearLectura("EST002", 15.1, 6.3, 7.5, 10.0, 1),
            crearLectura("EST003", 8.3, 3.9, 5.4, 9.2, 0),
            crearLectura("EST004", 12.0, 4.1, 4.9, 11.3, 0),
            crearLectura("EST005", 11.7, 5.0, 6.0, 13.5, 0)
        );

        lecturaRepository.saveAll(lecturas);
    }

    private Lectura crearLectura(String estacionId, double no, double no2, double vocs, double pm25, int horasAntes) {
        Lectura lectura = new Lectura();
        lectura.setEstacionId(estacionId);
        lectura.setTimeStamp(Instant.now().minus(horasAntes, ChronoUnit.HOURS));
        lectura.setNitricOxides(no);
        lectura.setNitrogenDioxides(no2);
        lectura.setVOCs_NMHC(vocs);
        lectura.setPM2_5(pm25);
        return lectura;
    }
}
