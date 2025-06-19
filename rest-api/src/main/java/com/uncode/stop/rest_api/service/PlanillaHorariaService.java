package com.uncode.stop.rest_api.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.uncode.stop.rest_api.entity.PlanillaHoraria;
import com.uncode.stop.rest_api.error.ServiceException;
import com.uncode.stop.rest_api.repository.PlanillaHorariaRepository;

@Service
public class PlanillaHorariaService extends CrudService<PlanillaHoraria, UUID> {

    public PlanillaHorariaService(PlanillaHorariaRepository repository) {
        super(repository);
    }

    @Override
    public void validate(PlanillaHoraria entity) {
        var entrada = entity.getEntrada();
        if (entrada == null) {
            throw new ServiceException("La entrada es requerida");
        }
        var salida = entity.getSalida();
        if (salida == null) {
            throw new ServiceException("La salida es requerida");
        }
        if (entrada.isAfter(salida)) {
            throw new ServiceException("La hora de entrada no puede ser posterior a la hora de salida");
        }
        var estadoAsistencia = entity.getEstadoAsistencia();
        if (estadoAsistencia == null) {
            throw new ServiceException("El estado de asistencia es requerido");
        }
        var empleado = entity.getEmpleado();
        if (empleado == null || empleado.getId() == null) {
            throw new ServiceException("El empleado es requerido");
        }
    }

}
