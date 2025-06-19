package com.uncode.stop.rest_api.repository;

import com.uncode.stop.rest_api.entity.MovimientoVisita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MovimientoVisitaRepository extends JpaRepository<MovimientoVisita, UUID> {
}
