package com.uncode.stop.cliente.dtos;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UnidadNegocioDTO {
	private UUID id;
	private String nombre;
	private DireccionDTO direccion = new DireccionDTO();
	private ServicioDTO servicio = new ServicioDTO(); 
}
