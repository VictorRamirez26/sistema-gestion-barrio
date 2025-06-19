package com.uncode.stop.rest_api.entity;

import com.uncode.stop.rest_api.service.Identifiable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SoftDelete;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@SoftDelete(columnName = "eliminado")
public class MovimientoVisita implements Identifiable<UUID> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull(message = "El tipo de movimiento es requerido")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoMovimiento tipoMovimiento;

    @NotNull(message = "La fecha de movimiento es requerida")
    @Column(nullable = false)
    private LocalDateTime fechaMovimiento;

    private String observacion;

    @NotNull(message = "El estado de movimiento es requerido")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoMovimiento estadoMovimiento;

    @NotNull(message = "El tipo de movilidad es requerido")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoMovilidad tipoMovilidad;

    private String descripcionMovilidad;

    @NotNull(message = "El tipo de visita es requerido")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoVisita tipoVisita;

    @NotNull(message = "El visitante es requerido")
    @ManyToOne(optional = false)
    private Visitante visitante;

    @ManyToOne(optional = false)
    private Inmueble inmueble;

}
