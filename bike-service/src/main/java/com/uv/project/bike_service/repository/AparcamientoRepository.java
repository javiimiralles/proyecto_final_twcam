package com.uv.project.bike_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uv.project.bike_service.domain.Aparcamiento;

public interface AparcamientoRepository extends JpaRepository<Aparcamiento, Integer> {

    List<Aparcamiento> findTop10ByOrderByNumBicisDesc();
}
