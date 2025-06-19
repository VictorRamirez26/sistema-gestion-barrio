package com.uncode.stop.cliente.dtos;

import java.util.UUID;

import com.uncode.stop.cliente.enums.TipoContacto;
import com.uncode.stop.cliente.enums.TipoEmpleado;
import com.uncode.stop.cliente.enums.TipoTelefono;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaisDTO {
	
	private UUID id;
	private String nombre;
}
