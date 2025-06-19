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
public class DireccionDTO {

	private UUID id;
	private String calle;
	private String numeracion;
	private String latitud;
	private String longitud;
	private LocalidadDTO localidad = new LocalidadDTO();
}
