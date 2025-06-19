package com.uncode.stop.rest_api.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uncode.stop.rest_api.entity.Departamento;

public interface DepartamentoRepository extends JpaRepository<Departamento, UUID>{

	Optional<Departamento> findByNombre(String nombre);
	
	List<Departamento> findByProvinciaId(UUID id);
}
