package com.uv.project.city_service.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uv.project.shared.domain.Estacion;
import com.uv.project.city_service.client.PollutionServiceClient;
import com.uv.project.city_service.utils.DistanceUtils;;


@Service
public class EstacionService {

    @Autowired
    private PollutionServiceClient pollutionServiceClient;

    @Autowired
    private DistanceUtils distanceUtils;

    public Estacion getEstacionMasCercana(double latitud, double longitud) {
        List<Estacion> estaciones = pollutionServiceClient.findEstaciones();

        if (estaciones == null || estaciones.isEmpty()) {
            return null;
        }

        Estacion masCercana = estaciones.get(0);
        double minDist = distanceUtils.calcularDistancia(latitud, longitud, masCercana.getLatitude(), masCercana.getLongitude());
        for (Estacion e : estaciones) {
            double dist = distanceUtils.calcularDistancia(latitud, longitud, e.getLatitude(), e.getLongitude());
            if (dist < minDist) {
                minDist = dist;
                masCercana = e;
            }
        }
        return masCercana;
    }

    public Estacion createEstacion(Estacion estacion, String token) {
        return pollutionServiceClient.createEstacion(estacion);
    }

    public void deleteEstacion(Integer id, String token) {
        pollutionServiceClient.deleteEstacion(id);
    }
}
