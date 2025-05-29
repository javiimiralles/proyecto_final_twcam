package com.uv.project.city_service.domain;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "aggregatedData")
public class AggregatedData {
    
    @Id
    private String id;

    private LocalDateTime timestamp;

    private List<AggregatedEntry> aggregatedData;
}
