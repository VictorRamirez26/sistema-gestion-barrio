package com.uncode.stop.rest_api.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uncode.stop.rest_api.entity.Inmueble;

public interface InmuebleRepository extends JpaRepository<Inmueble, UUID> {

    Optional<Inmueble> findByUnidadDeNegocioIdAndNumeracionAndPisoAndDepto(UUID id, String numeracion, String piso, String depto);
    
    List<Inmueble> findByUnidadDeNegocioId(UUID id);
}
