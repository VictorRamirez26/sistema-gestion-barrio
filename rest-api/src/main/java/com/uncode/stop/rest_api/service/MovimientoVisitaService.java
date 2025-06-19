package com.uncode.stop.rest_api.service;

import com.uncode.stop.rest_api.entity.MovimientoVisita;
import com.uncode.stop.rest_api.error.ServiceException;
import com.uncode.stop.rest_api.repository.MovimientoVisitaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MovimientoVisitaService extends CrudService<MovimientoVisita, UUID> {

    public MovimientoVisitaService(MovimientoVisitaRepository repository) {
        super(repository);
    }

    @Override
    public void validate(MovimientoVisita entity) {
        var tipoMovimiento = entity.getTipoMovimiento();
        if (tipoMovimiento == null) {
            throw new ServiceException("El tipo de movimiento es requerido");
        }

        var fechaMovimiento = entity.getFechaMovimiento();
        if (fechaMovimiento == null) {
            throw new ServiceException("La fecha de movimiento es requerida");
        }

        var estadoMovimiento = entity.getEstadoMovimiento();
        if (estadoMovimiento == null) {
            throw new ServiceException("El estado de movimiento es requerido");
        }

        var tipoMovilidad = entity.getTipoMovilidad();
        if (tipoMovilidad == null) {
            throw new ServiceException("El tipo de movilidad es requerido");
        }

        var visitante = entity.getVisitante();
        if (visitante == null) {
            throw new ServiceException("El visitante es requerido");
        }

        var tipoVisita = entity.getTipoVisita();
        if (tipoVisita == null) {
            throw new ServiceException("El tipo de visita es requerido");
        }

        var inmueble = entity.getInmueble();

        if (inmueble == null) {
            throw new ServiceException("El inmueble es requerido");
        }

    }

}
