package com.uv.project.city_service.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uv.project.city_service.client.BikeServiceClient;
import com.uv.project.city_service.client.EventoServiceClient;
import com.uv.project.city_service.client.LecturaServiceClient;
import com.uv.project.city_service.repository.AggregatedDataRepository;
import com.uv.project.shared.domain.AggregatedData;
import com.uv.project.shared.domain.AggregatedEntry;
import com.uv.project.shared.domain.AirQuality;
import com.uv.project.shared.domain.Aparcamiento;
import com.uv.project.shared.domain.Estacion;
import com.uv.project.shared.domain.Evento;
import com.uv.project.shared.domain.Lectura;

@Service
public class AggregatedDataService {
    
    @Autowired
    private AggregatedDataRepository aggregatedDataRepository;

    @Autowired
    private BikeServiceClient bikeServiceClient;

    @Autowired
    private EventoServiceClient eventoServiceClient;

    @Autowired
    private EstacionService estacionService;

    @Autowired
    private LecturaServiceClient lecturaServiceClient;

    private Logger logger = Logger.getLogger(AggregatedDataService.class.getName());

    public AggregatedData getLastAggregatedData() {
        logger.info("Obteniendo los datos agregados más recientes");
        return aggregatedDataRepository.findTopByOrderByTimestampDesc();
    }

    public AggregatedData aggregateData() {
        logger.info("Guardando nuevos datos agregados");

        AggregatedData aggregatedData = new AggregatedData();
        aggregatedData.setTimestamp(LocalDateTime.now());
        List<Aparcamiento> aparcamientos = bikeServiceClient.findAparcamientos();
        if (aparcamientos == null || aparcamientos.isEmpty()) {
            logger.warning("No se encontraron aparcamientos para agregar datos");
            return null;
        }

        List<AggregatedEntry> aggregatedEntries = new ArrayList<>();
        for (Aparcamiento aparcamiento : aparcamientos) {
            List<Evento> eventos = eventoServiceClient.findEventosPorAparcamiento(aparcamiento.getId());
            if (eventos != null && !eventos.isEmpty()) {
                // Calcular el promedio de bicicletas disponibles
                int totalEventos = eventos.size();
                int totalBicis = eventos.stream()
                    .mapToInt(evento -> evento.getBicisDisponibles())
                    .sum();
                double averageBicis = totalBicis / totalEventos;
    
                // Obtener la estación más cercana
                Estacion estacionMasCercana = estacionService.getEstacionMasCercana(aparcamiento.getLatitud(), aparcamiento.getLongitud());
                
                List<Lectura> lecturas = lecturaServiceClient.findLecturasPorEstacion(estacionMasCercana.getId());
                if (lecturas != null && !lecturas.isEmpty()) {
                    // Calcular el promedio de contaminación
                    int totalLecturas = lecturas.size();
                    double nitricOxides = lecturas.stream()
                        .mapToDouble(Lectura::getNitricOxides)
                        .average()
                        .orElse(0.0);
                    double nitrogenDioxides = lecturas.stream()
                        .mapToDouble(Lectura::getNitrogenDioxides)
                        .average()
                        .orElse(0.0);
                    double VOCs_NMHC = lecturas.stream()
                        .mapToDouble(Lectura::getVOCs_NMHC)
                        .average()
                        .orElse(0.0);
                    double PM2_5 = lecturas.stream()
                        .mapToDouble(Lectura::getPM2_5)
                        .average()
                        .orElse(0.0);
        
                    AirQuality airQuality = new AirQuality();
                    airQuality.setNitricOxides(nitricOxides / totalLecturas);
                    airQuality.setNitrogenDioxides(nitrogenDioxides / totalLecturas);
                    airQuality.setVOCs_NMHC(VOCs_NMHC / totalLecturas);
                    airQuality.setPM2_5(PM2_5 / totalLecturas);
        
                    AggregatedEntry aggregatedEntry = new AggregatedEntry();
                    aggregatedEntry.setIdAparcamiento(aparcamiento.getId());
                    aggregatedEntry.setIdEstacion(estacionMasCercana.getId());
                    aggregatedEntry.setAverageBikesAvailable(averageBicis);
                    aggregatedEntry.setAirQuality(airQuality);
        
                    aggregatedEntries.add(aggregatedEntry);
                } 
            }


        }

        aggregatedData.setAggregatedData(aggregatedEntries);
        
        return aggregatedDataRepository.save(aggregatedData);
    }
}
