package com.uv.project.shared.domain;

import org.springframework.data.mongodb.core.mapping.Document;

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

    public Evento(String id, int idAparcamiento, OperacionEnum operacion, int huecosDisponibles, int bicisDisponibles, LocalDateTime fecha) {
        this.id = id;
        this.idAparcamiento = idAparcamiento;
        this.operacion = operacion;
        this.huecosDisponibles = huecosDisponibles;
        this.bicisDisponibles = bicisDisponibles;
        this.fecha = fecha;
    }

}
