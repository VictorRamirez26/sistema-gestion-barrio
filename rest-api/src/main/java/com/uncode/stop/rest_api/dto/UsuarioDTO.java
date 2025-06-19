package com.uncode.stop.rest_api.dto;

import com.uncode.stop.rest_api.entity.Rol;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {

    @NotBlank(message = "El nombre no puede estar vacío")
    private String cuenta;
    @NotBlank(message = "La clave no puede estar vacía")
    private String clave;
    private String confirmarClave;
    @NotNull(message = "El rol es requerido")
    private Rol rol;
}
