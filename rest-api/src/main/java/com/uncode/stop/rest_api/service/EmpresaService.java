package com.uncode.stop.rest_api.service;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.uncode.stop.rest_api.dto.EmpresaDTO;
import com.uncode.stop.rest_api.entity.Empresa;
import com.uncode.stop.rest_api.error.NotFoundException;
import com.uncode.stop.rest_api.error.ServiceException;
import com.uncode.stop.rest_api.repository.EmpresaRepository;

@Service
public class EmpresaService extends CRUDService2<Empresa, UUID, EmpresaDTO> {

    private final EmpresaRepository repository;
    private final ModelMapper modelMapper;

    public EmpresaService(EmpresaRepository repository, ModelMapper modelMapper) {
        super(repository);
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void validate(Empresa entity) {
        var nombre = entity.getNombre();
        if (nombre == null || nombre.isBlank()) {
            throw new ServiceException("El nombre es requerido");
        }
    }

    @Override
    protected Empresa toEntity(EmpresaDTO dto) {
        return modelMapper.map(dto, Empresa.class);
    }

    @Override
    protected Empresa toEntity(UUID id, EmpresaDTO dto) {
        var entity = repository.findById(id).orElseThrow(() -> new NotFoundException("No se encontró la empresa"));
        modelMapper.map(dto, entity);
        return entity;
    }

}
