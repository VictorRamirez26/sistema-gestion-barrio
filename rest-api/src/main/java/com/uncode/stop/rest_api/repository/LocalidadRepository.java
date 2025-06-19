package com.uncode.stop.rest_api.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uncode.stop.rest_api.entity.Localidad;

public interface LocalidadRepository extends JpaRepository<Localidad, UUID>{

	Optional<Localidad> findByNombre(String nombre);
	
	List<Localidad> findByDepartamentoId(UUID id);
}
