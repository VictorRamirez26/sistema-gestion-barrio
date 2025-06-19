package com.uncode.stop.rest_api.entity;

import java.util.UUID;

import org.hibernate.annotations.SoftDelete;

import com.uncode.stop.rest_api.service.Identifiable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@SoftDelete(columnName = "eliminado")
@Getter
@Setter
public class Direccion implements Identifiable<UUID>{
	
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    private String calle;
    private String numeracion;
    private String latitud;
    private String longitud;
    @ManyToOne
    private Localidad localidad;
}
