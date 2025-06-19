package com.uncode.stop.rest_api.entity;

import java.util.UUID;
import java.time.LocalDateTime;

import org.hibernate.annotations.SoftDelete;

import com.uncode.stop.rest_api.service.Identifiable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@SoftDelete(columnName = "eliminado")
public class PlanillaHoraria implements Identifiable<UUID> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull(message = "La entrada es requerida")
    @Column(nullable = false)
    private LocalDateTime entrada;

    @NotNull(message = "La salida es requerida")
    @Column(nullable = false)
    private LocalDateTime salida;

    @NotNull(message = "El estado de la asistencia es requerido")
    @Column(nullable = false)
    private EstadoAsistencia estadoAsistencia;

    private String observacionAsistencia;

    @NotNull(message = "El empleado es requerido")
    @ManyToOne(optional = false)
    private Empleado empleado;

}
