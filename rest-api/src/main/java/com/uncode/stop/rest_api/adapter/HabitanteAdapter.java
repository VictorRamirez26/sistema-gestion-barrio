package com.uncode.stop.rest_api.adapter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.uncode.stop.rest_api.entity.Habitante;
import com.uncode.stop.rest_api.service.InmuebleService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class HabitanteAdapter implements DTOAdapter<Habitante, Habitante> {

    private final ModelMapper mapper;
    private final InmuebleService inmuebleService;

    @Override
    public Habitante toEntity(Habitante dto) {
        var entity = mapper.map(dto, Habitante.class);
        entity.setInmueble(inmuebleService.readOne(dto.getInmueble().getId()));
        return entity;
    }

    @Override
    public Habitante toDTO(Habitante entity) {
        return entity;
    }

}
