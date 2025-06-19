package com.uncode.stop.rest_api.entity;

import java.util.UUID;

import org.hibernate.annotations.SoftDelete;

import com.uncode.stop.rest_api.service.Identifiable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@SoftDelete(columnName = "eliminado")
@Getter
@Setter
public class Empresa implements Identifiable<UUID> {

    @Id
    @GeneratedValue
    private UUID id;
    private String nombre;

}
