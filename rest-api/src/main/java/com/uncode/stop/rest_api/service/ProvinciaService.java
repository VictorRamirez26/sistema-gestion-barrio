package com.uncode.stop.rest_api.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.uncode.stop.rest_api.entity.Pais;
import com.uncode.stop.rest_api.entity.Provincia;
import com.uncode.stop.rest_api.error.ServiceException;
import com.uncode.stop.rest_api.repository.ProvinciaRepository;

@Service
public class ProvinciaService extends CrudService<Provincia, UUID> {

	private final ProvinciaRepository repository;
	
    public ProvinciaService(ProvinciaRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public void validate(Provincia entity) {
        var nombre = entity.getNombre();
        if (nombre == null || nombre.isBlank()) {
            throw new ServiceException("El nombre es requerido");
        }
        
		var existing = repository.findByNombre(nombre);
		if (existing.isPresent() && !existing.get().getId().equals(entity.getId())) {
			throw new ServiceException("El nombre ya existe");
		}
		
		var pais = entity.getPais();
		if (pais == null) {
			throw new ServiceException("El pais es requerido");
		}
    }
    
    public List<Provincia> listarProvinciasPorPais(UUID id) {
    	return repository.findByPaisId(id);
	}

}
