package com.uncode.stop.cliente.dtos;


import java.util.UUID;

import com.uncode.stop.cliente.enums.TipoContacto;
import com.uncode.stop.cliente.enums.TipoTelefono;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactoDTO {

	private UUID id;
    private TipoContacto tipoContacto;
    private String observacion;

    private String email;

    private String telefono;

    private TipoTelefono tipoTelefono;
}
