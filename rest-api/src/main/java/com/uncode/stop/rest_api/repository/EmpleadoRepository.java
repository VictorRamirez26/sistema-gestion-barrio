package com.uncode.stop.rest_api.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uncode.stop.rest_api.entity.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, UUID> {

    Optional<Empleado> findByLegajo(String legajo);

}
