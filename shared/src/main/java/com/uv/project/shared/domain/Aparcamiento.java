package com.uv.project.shared.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "aparcamientos")
public class Aparcamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(name = "direccion")
    private String direccion;

    @Column(name = "capacidad")
    private int capacidad;

    @Column(name = "num_bicis")
    private int numBicis;

    @Column(name = "latitud")
    private float latitud;

    @Column(name = "longitud")
    private float longitud;
}
