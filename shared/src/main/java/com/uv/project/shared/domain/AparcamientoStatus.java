package com.uv.project.shared.domain;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AparcamientoStatus {
    
    private int id;

    private int huecosDisponibles;

    private int bicisDisponibles;

    private LocalDateTime fecha;
}
