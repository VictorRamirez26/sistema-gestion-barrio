package com.uncode.stop.rest_api.dto;

import com.uncode.stop.rest_api.entity.TipoContacto;
import com.uncode.stop.rest_api.entity.TipoTelefono;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactoDTO {

    @NotNull(message = "El tipo de contacto es requerido")
    private TipoContacto tipoContacto;
    private String observacion;

    private String email;

    private String telefono;

    private TipoTelefono tipoTelefono;

}
