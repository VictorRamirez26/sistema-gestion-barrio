package com.uncode.stop.rest_api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Empleado extends Persona {

    private String legajo;

    @Enumerated(EnumType.STRING)
    private TipoEmpleado tipoEmpleado;

    @ManyToOne
    private UnidadDeNegocio unidadDeNegocio;

}
