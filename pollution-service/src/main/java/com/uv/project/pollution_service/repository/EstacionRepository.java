package com.uv.project.pollution_service.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.uv.project.pollution_service.domain.Estacion;


public interface EstacionRepository extends JpaRepository<Estacion, Integer> {
}