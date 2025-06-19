package com.uncode.stop.rest_api.adapter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.uncode.stop.rest_api.entity.MovimientoVisita;
import com.uncode.stop.rest_api.service.VisitanteService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MovimientoVisitaAdapter implements DTOAdapter<MovimientoVisita, MovimientoVisita> {

    private final ModelMapper mapper;
    private final VisitanteService visitanteService;

    @Override
    public MovimientoVisita toDTO(MovimientoVisita entity) {
        return entity;
    }

    @Override
    public MovimientoVisita toEntity(MovimientoVisita dto) {
        var entity = mapper.map(dto, MovimientoVisita.class);
        entity.setVisitante(visitanteService.readOne(dto.getVisitante().getId()));
        return entity;
    }

}
