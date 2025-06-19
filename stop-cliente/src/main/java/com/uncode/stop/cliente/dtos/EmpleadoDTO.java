package com.uncode.stop.cliente.dtos;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.uncode.stop.cliente.enums.TipoEmpleado;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmpleadoDTO {
	private UUID id;
	private String nombre;
	private String apellido;
	private String legajo;
	private TipoEmpleado tipoEmpleado;
	private UnidadNegocioDTO unidadDeNegocio = new UnidadNegocioDTO();
	private List<ContactoDTO> contactos = new ArrayList<ContactoDTO>();
	private UsuarioDTO usuario = new UsuarioDTO();
}
