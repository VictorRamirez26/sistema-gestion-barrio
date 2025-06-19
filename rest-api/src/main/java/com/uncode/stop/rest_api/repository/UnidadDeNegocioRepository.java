package com.uncode.stop.rest_api.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uncode.stop.rest_api.entity.UnidadDeNegocio;

public interface UnidadDeNegocioRepository extends JpaRepository<UnidadDeNegocio, UUID> {

}
