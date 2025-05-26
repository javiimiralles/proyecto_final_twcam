package com.uv.project.bike_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uv.project.bike_service.domain.Aparcamiento;

@Repository
public interface AparcamientoRepository extends JpaRepository<Aparcamiento, Integer> {

    List<Aparcamiento> findTop10ByOrderByNumBicisDesc();
}
