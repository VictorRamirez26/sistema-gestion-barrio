package com.uncode.stop.rest_api.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.uncode.stop.rest_api.entity.UnidadDeNegocio;
import com.uncode.stop.rest_api.error.ServiceException;
import com.uncode.stop.rest_api.repository.UnidadDeNegocioRepository;

@Service
public class UnidadDeNegocioService extends CrudService<UnidadDeNegocio, UUID> {
	
	
    public UnidadDeNegocioService(UnidadDeNegocioRepository repository) {
        super(repository);
    }

    @Override
    public void validate(UnidadDeNegocio entity) {
        var nombre = entity.getNombre();
        if (nombre == null || nombre.isBlank()) {
            throw new ServiceException("El nombre es requerido");
        }
        
        var direccion = entity.getDireccion();
        if (direccion == null) {
        	throw new ServiceException("La direccion es requerida");
		}
        
    }

}
