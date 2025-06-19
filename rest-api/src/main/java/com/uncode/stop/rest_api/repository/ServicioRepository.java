package com.uncode.stop.rest_api.repository;

import com.uncode.stop.rest_api.entity.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ServicioRepository extends JpaRepository<Servicio, UUID> {

    Optional<Servicio> findByNombre(String nombre);

}
