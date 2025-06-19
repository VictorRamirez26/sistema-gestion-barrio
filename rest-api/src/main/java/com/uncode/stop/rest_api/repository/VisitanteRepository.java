package com.uncode.stop.rest_api.repository;

import com.uncode.stop.rest_api.entity.Visitante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface VisitanteRepository extends JpaRepository<Visitante, UUID> {

    Optional<Visitante> findByNumeroDeDocumento(String numeroDeDocumento);
}
