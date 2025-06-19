package com.uncode.stop.cliente.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServicioImagenDTO {
    private UUID id;
    private String nombre;
    private UUID imagenId;
}
