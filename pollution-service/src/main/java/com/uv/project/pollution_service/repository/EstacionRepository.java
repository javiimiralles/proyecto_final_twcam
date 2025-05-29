package com.uv.project.pollution_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uv.project.shared.domain.Estacion;


public interface EstacionRepository extends JpaRepository<Estacion, Integer> {
}