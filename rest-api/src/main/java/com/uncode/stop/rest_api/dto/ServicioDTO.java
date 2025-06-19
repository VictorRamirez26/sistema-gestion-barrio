package com.uncode.stop.rest_api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ServicioDTO {

    private UUID id;
    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;
    @NotBlank(message = "La descripción no puede estar vacía")
    private UUID imagenId;

}
