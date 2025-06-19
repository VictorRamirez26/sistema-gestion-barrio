package com.uncode.stop.rest_api.repository;

import com.uncode.stop.rest_api.entity.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ImagenRepository extends JpaRepository<Imagen, UUID> {
}
