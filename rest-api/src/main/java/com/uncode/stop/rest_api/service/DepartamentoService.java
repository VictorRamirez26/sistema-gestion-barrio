package com.uncode.stop.rest_api.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.uncode.stop.rest_api.entity.Departamento;
import com.uncode.stop.rest_api.error.ServiceException;
import com.uncode.stop.rest_api.repository.DepartamentoRepository;

@Service
public class DepartamentoService extends CrudService<Departamento, UUID>{

	private final DepartamentoRepository repository;
	
	public DepartamentoService(DepartamentoRepository repository) {
		super(repository);
		this.repository = repository;
	}

	@Override
	public void validate(Departamento entity) {
        var nombre = entity.getNombre();
        if (nombre == null || nombre.isBlank()) {
            throw new ServiceException("El nombre es requerido");
        }
        
		var existing = repository.findByNombre(nombre);
		if (existing.isPresent() && !existing.get().getId().equals(entity.getId())) {
			throw new ServiceException("Ya existe un departamento con ese nombre");
		}
        
        var provincia = entity.getProvincia();
        if (provincia == null) {
        	throw new ServiceException("La provincia es requerida");
		}
	}
	
    public List<Departamento> listarDepartamentosPorProvincia(UUID id) {
    	return repository.findByProvinciaId(id);
	}

}
