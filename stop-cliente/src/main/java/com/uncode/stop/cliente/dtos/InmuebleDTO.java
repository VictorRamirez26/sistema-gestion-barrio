package com.uncode.stop.cliente.dtos;

import com.uncode.stop.cliente.enums.EstadoInmueble;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InmuebleDTO {

    private UUID id;
    private String numeracion;
    private String piso;
    private String depto;
    private EstadoInmueble estadoInmueble;
    private UnidadNegocioDTO unidadDeNegocio = new UnidadNegocioDTO();

}
