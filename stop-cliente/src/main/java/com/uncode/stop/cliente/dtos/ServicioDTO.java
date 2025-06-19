package com.uncode.stop.cliente.dtos;

import java.util.UUID;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServicioDTO {
	private UUID id;
    private String nombre;
	private String nombreImagen;
	private String mimeImagen;
	private byte[] contenidoImagen;
}
