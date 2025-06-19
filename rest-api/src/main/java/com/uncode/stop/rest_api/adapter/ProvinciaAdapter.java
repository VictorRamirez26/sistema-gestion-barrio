package com.uncode.stop.rest_api.adapter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.uncode.stop.rest_api.entity.Provincia;
import com.uncode.stop.rest_api.service.PaisService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProvinciaAdapter implements DTOAdapter<Provincia, Provincia> {

    private final ModelMapper mapper;
    private final PaisService paisService;

    @Override
    public Provincia toDTO(Provincia entity) {
        return entity;
    }

    @Override
    public Provincia toEntity(Provincia dto) {
        var entity = mapper.map(dto, Provincia.class);
        entity.setPais(paisService.readOne(dto.getPais().getId()));
        return entity;
    }

}
