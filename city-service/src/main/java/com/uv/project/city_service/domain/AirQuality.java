package com.uv.project.city_service.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class AirQuality {
    
    @JsonProperty("nitricOxides")
    private double nitricOxides;

    @JsonProperty("nitrogenDioxides")
    private double nitrogenDioxides;

    @JsonProperty("VOCs_NMHC")
    private double VOCs_NMHC;

    @JsonProperty("PM2_5")
    private double PM2_5;
}
