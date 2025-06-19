package com.uncode.stop.rest_api.dto;

import com.uncode.stop.rest_api.entity.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class MovimientoVisitaDTO {

    private UUID id;
    @NotNull(message = "El tipo de movimiento es requerido")
    private TipoMovimiento tipoMovimiento;
    @NotNull(message = "La fecha de movimiento es requerida")
    private LocalDateTime fechaMovimiento;
    private String observacion;
    @NotNull(message = "El estado de movimiento es requerido")
    private EstadoMovimiento estadoMovimiento;
    @NotNull(message = "El tipo de movilidad es requerido")
    private TipoMovilidad tipoMovilidad;
    private String descripcionMovilidad;
    @NotNull(message = "El tipo de visita es requerido")
    private TipoVisita tipoVisita;
    @NotNull(message = "El visitante es requerido")
    private UUID visitanteId;
    @NotNull(message = "El inmueble es requerido")
    private UUID inmuebleId;
}
