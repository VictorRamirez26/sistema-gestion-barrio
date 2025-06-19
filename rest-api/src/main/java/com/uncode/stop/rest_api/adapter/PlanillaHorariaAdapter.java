package com.uncode.stop.rest_api.adapter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;

import com.uncode.stop.rest_api.entity.PlanillaHoraria;
import com.uncode.stop.rest_api.service.EmpleadoService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PlanillaHorariaAdapter implements DTOAdapter<PlanillaHoraria, PlanillaHoraria> {

    private final ModelMapper mapper;
    private final EmpleadoService empleadoService;

    @Override
    public PlanillaHoraria toDTO(PlanillaHoraria entity) {
        return entity;
    }

    @Override
    public PlanillaHoraria toEntity(PlanillaHoraria dto) {
        var entity = mapper.map(dto, PlanillaHoraria.class);
        entity.setEmpleado(empleadoService.read(dto.getEmpleado().getId()));
        return entity;
    }

}
