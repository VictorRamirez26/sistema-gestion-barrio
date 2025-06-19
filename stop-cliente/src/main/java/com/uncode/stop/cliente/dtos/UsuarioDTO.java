package com.uncode.stop.cliente.dtos;

import java.util.UUID;

import com.uncode.stop.cliente.enums.Rol;
import com.uncode.stop.cliente.enums.TipoEmpleado;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDTO {

	private String cuenta;
	private String clave;
	private String confirmarClave;
	private Rol rol;
}
