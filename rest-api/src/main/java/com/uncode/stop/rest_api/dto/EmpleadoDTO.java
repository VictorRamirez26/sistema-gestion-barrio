package com.uncode.stop.rest_api.dto;

import java.io.Serializable;
import java.util.UUID;

import com.uncode.stop.rest_api.entity.TipoEmpleado;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmpleadoDTO extends PersonaDTO implements Serializable  {

    
    @NotBlank(message = "El legajo no puede estar vacío")
    private String legajo;
    @NotNull(message = "El tipo de empleado es requerido")
    private TipoEmpleado tipoEmpleado;
    private IdentifierDTO<UUID> unidadDeNegocio = new IdentifierDTO<>();

}
