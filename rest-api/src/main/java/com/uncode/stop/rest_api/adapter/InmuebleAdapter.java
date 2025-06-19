package com.uncode.stop.rest_api.adapter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.uncode.stop.rest_api.entity.Inmueble;
import com.uncode.stop.rest_api.service.UnidadDeNegocioService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class InmuebleAdapter implements DTOAdapter<Inmueble, Inmueble> {
    
    private final ModelMapper mapper;    
    private final UnidadDeNegocioService unidadDeNegocioService;
    
    @Override
    public Inmueble toDTO(Inmueble entity) {
        return entity;
    }

    @Override
    public Inmueble toEntity(Inmueble dto) {
        var entity = mapper.map(dto, Inmueble.class);
        entity.setUnidadDeNegocio(unidadDeNegocioService.readOne(dto.getUnidadDeNegocio().getId()));
        return entity;
    }

}
