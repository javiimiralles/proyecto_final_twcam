package com.uv.project.shared.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class AggregatedEntry {
    
    @JsonProperty("idAparcamiento")
    private int idAparcamiento;

    @JsonProperty("idEstacion")
    private int idEstacion;

    @JsonProperty("averageBikesAvailable")
    private double averageBikesAvailable;

    @JsonProperty("airQuality")
    private AirQuality airQuality;
}
