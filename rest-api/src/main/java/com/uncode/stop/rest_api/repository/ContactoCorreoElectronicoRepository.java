package com.uncode.stop.rest_api.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uncode.stop.rest_api.entity.ContactoCorreoElectronico;

public interface ContactoCorreoElectronicoRepository extends JpaRepository<ContactoCorreoElectronico, UUID> {

    Optional<ContactoCorreoElectronico> findByEmail(String email);

}
