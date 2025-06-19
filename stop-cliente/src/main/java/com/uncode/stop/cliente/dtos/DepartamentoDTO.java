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
public class DepartamentoDTO {
	private UUID id;
	private String nombre;
	private ProvinciaDTO provincia = new ProvinciaDTO();
}
