package com.uv.project.pollution_service.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@Document(collection = "lecturas")
public class Lectura {

    @Id
    private String id;

    private String estacionId;

    private Instant timeStamp;

    private double nitricOxides;
    private double nitrogenDioxides;
    private double VOCs_NMHC;
    private double PM2_5;
}
