package com.uncode.stop.rest_api.adapter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.uncode.stop.rest_api.entity.Localidad;
import com.uncode.stop.rest_api.service.DepartamentoService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LocalidadAdapter implements DTOAdapter<Localidad, Localidad>{
	
	private final ModelMapper mapper;
	private final DepartamentoService departamentoService;

	@Override
	public Localidad toDTO(Localidad entity) {
		return entity;
	}

	@Override
	public Localidad toEntity(Localidad dto) {
		var entity = mapper.map(dto, Localidad.class);
		entity.setDepartamento(departamentoService.readOne(dto.getDepartamento().getId()));
		return entity;
	}
	
}
