package com.uncode.stop.cliente.dtos;

import com.uncode.stop.cliente.enums.EstadoMovimiento;
import com.uncode.stop.cliente.enums.TipoMovilidad;
import com.uncode.stop.cliente.enums.TipoMovimiento;
import com.uncode.stop.cliente.enums.TipoVisita;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VisitaDTO {

    private UUID id;
    private TipoMovimiento tipoMovimiento;
    private LocalDateTime fechaMovimiento;
    private String observacion;
    private EstadoMovimiento estadoMovimiento;
    private TipoMovilidad tipoMovilidad;
    private String descripcionMovilidad;
    private TipoVisita tipoVisita;
    private InmuebleDTO inmueble;
    private VisitanteDTO visitante;
}
