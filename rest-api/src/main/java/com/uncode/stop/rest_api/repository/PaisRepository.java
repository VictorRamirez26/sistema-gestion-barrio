package com.uncode.stop.rest_api.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.uncode.stop.rest_api.entity.Pais;

public interface PaisRepository extends JpaRepository<Pais, UUID> {
	
	@Query("SELECT p FROM Pais p WHERE p.nombre = :nombre")
	Optional<Pais> findByName(@Param("nombre") String nombre);

    Optional<Pais> findByNombre(String nombre);
}
