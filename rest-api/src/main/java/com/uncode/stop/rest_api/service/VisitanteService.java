package com.uncode.stop.rest_api.service;

import com.uncode.stop.rest_api.entity.Visitante;
import com.uncode.stop.rest_api.error.ServiceException;
import com.uncode.stop.rest_api.repository.VisitanteRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class VisitanteService extends CrudService<Visitante, UUID> {

    private final VisitanteRepository repository;

    public VisitanteService(VisitanteRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public void validate(Visitante entity) {
        var nombre = entity.getNombre();
        if (nombre == null || nombre.isBlank()) {
            throw new ServiceException("El nombre es requerido");
        }

        var apellido = entity.getApellido();
        if (apellido == null || apellido.isBlank()) {
            throw new ServiceException("El apellido es requerido");
        }

        var numeroDeDocumento = entity.getNumeroDeDocumento();
        if (numeroDeDocumento == null || numeroDeDocumento.isBlank()) {
            throw new ServiceException("El numero de documento es requerido");
        }

        var existing = repository.findByNumeroDeDocumento(numeroDeDocumento);
        if (existing.isPresent() && !existing.get().getId().equals(entity.getId())) {
            throw new ServiceException("Ya existe un visitante con ese numero de documento");
        }

    }

}
