package com.uncode.stop.rest_api.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.uncode.stop.rest_api.entity.Persona;

public interface PersonaRepository extends JpaRepository<Persona, UUID> {

    @Query("SELECT p FROM Persona p JOIN p.contactos c WHERE TYPE(c) = ContactoCorreoElectronico AND c.email = :email")
    Optional<Persona> findByEmail(@Param("email") String email);
}
