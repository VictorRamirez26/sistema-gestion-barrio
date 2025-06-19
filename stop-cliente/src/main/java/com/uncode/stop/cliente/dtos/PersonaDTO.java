package com.uncode.stop.cliente.dtos;


import java.util.ArrayList;
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
public class PersonaDTO {

	private UUID id;
    private String nombre;
    private String apellido;
    private List<ContactoDTO> contactos = new ArrayList<ContactoDTO>();
    private String tipoPersona;
}
