package com.uv.project.bike_service.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import com.uv.project.bike_service.enums.OperacionEnum;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
@Document(collection = "eventos")
public class Evento {
    
    @Id
    private String id;

    private int idAparcamiento;

    private OperacionEnum operacion;

    private int huecosDisponibles;

    private int bicisDisponibles;

    private LocalDateTime fecha;

}
