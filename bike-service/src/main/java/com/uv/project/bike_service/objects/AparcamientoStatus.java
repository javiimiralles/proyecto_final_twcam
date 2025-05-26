package com.uv.project.bike_service.objects;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AparcamientoStatus {
    
    private int id;

    private int huecosDisponibles;

    private int bicisDisponibles;

    private LocalDateTime fecha;
}
