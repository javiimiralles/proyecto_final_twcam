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
            crearLectura(1, 10.5, 5.2, 6.8, 12.1, 2),
            crearLectura(2, 15.1, 6.3, 7.5, 10.0, 1),
            crearLectura(3, 8.3, 3.9, 5.4, 9.2, 0),
            crearLectura(4, 12.0, 4.1, 4.9, 11.3, 0),
            crearLectura(5, 11.7, 5.0, 6.0, 13.5, 0),
            crearLectura(5, 14.3, 2.0, 5.0, 13.5, 3)
        );

        lecturaRepository.saveAll(lecturas);
    }

    private Lectura crearLectura(int estacionId, double no, double no2, double vocs, double pm25, int horasAntes) {
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
