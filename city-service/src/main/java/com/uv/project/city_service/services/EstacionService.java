package com.uv.project.city_service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uv.project.shared.domain.Estacion;
import com.uv.project.city_service.client.PollutionServiceClient;;


@Service
public class EstacionService {

    @Autowired
    private PollutionServiceClient pollutionServiceClient;

    public Estacion createEstacion(Estacion estacion) {
        return pollutionServiceClient.createEstacion(estacion);
    }

    public void deleteEstacion(Integer id) {
        pollutionServiceClient.deleteEstacion(id);
    }
}
