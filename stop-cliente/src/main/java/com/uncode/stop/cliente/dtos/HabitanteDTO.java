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
public class HabitanteDTO {
	private UUID id;
	private String nombre;
	private String apellido;
	private InmuebleDTO inmueble = new InmuebleDTO();
	private List<ContactoDTO> contactos = new ArrayList<ContactoDTO>();
	private UsuarioDTO usuario = new UsuarioDTO();
}
