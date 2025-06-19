package com.uncode.stop.rest_api.service;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.uncode.stop.rest_api.dto.EmpleadoDTO;
import com.uncode.stop.rest_api.entity.Empleado;
import com.uncode.stop.rest_api.entity.UnidadDeNegocio;
import com.uncode.stop.rest_api.error.NotFoundException;
import com.uncode.stop.rest_api.error.ServiceException;
import com.uncode.stop.rest_api.repository.EmpleadoRepository;

@Service
public class EmpleadoService extends CRUDService2<Empleado, UUID, EmpleadoDTO> {

    private final EmpleadoRepository repository;
    private final PersonaService personaService;
    private final ModelMapper modelMapper;

    public EmpleadoService(EmpleadoRepository repository, PersonaService personaService, ModelMapper modelMapper) {
        super(repository);
        this.repository = repository;
        this.personaService = personaService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void validate(Empleado entity) {
        personaService.validate(entity);

        var legajo = entity.getLegajo();
        if (legajo == null || legajo.isBlank()) {
            throw new ServiceException("El legajo es requerido");
        }

        var existing = repository.findByLegajo(legajo);
        if (existing.isPresent() && !existing.get().getId().equals(entity.getId())) {
            throw new ServiceException("El legajo ya está en uso");
        }

        var tipoEmpleado = entity.getTipoEmpleado();
        if (tipoEmpleado == null) {
            throw new ServiceException("El tipo de empleado es requerido");
        }
    }

    @Override
    protected Empleado toEntity(EmpleadoDTO dto) {
        var entity = modelMapper.map(dto, Empleado.class);
        var unidadDeNegocio = dto.getUnidadDeNegocio();
        if (unidadDeNegocio != null) {
            entity.setUnidadDeNegocio(new UnidadDeNegocio());
            entity.getUnidadDeNegocio().setId(unidadDeNegocio.getId());
        }
        return entity;
    }

    @Override
    protected Empleado toEntity(UUID id, EmpleadoDTO dto) {
        var entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Mo se encontró el empleado"));
        modelMapper.map(dto, entity);
        var unidadDeNegocio = dto.getUnidadDeNegocio();
        if (unidadDeNegocio == null) {
            entity.setUnidadDeNegocio(null);
        } else {
            if (entity.getUnidadDeNegocio() == null) {
                entity.setUnidadDeNegocio(new UnidadDeNegocio());
            }
            entity.getUnidadDeNegocio().setId(unidadDeNegocio.getId());
        }
        return entity;
    }

}
