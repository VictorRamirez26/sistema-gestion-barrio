package com.uncode.stop.rest_api.adapter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.uncode.stop.rest_api.entity.Direccion;
import com.uncode.stop.rest_api.service.LocalidadService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DireccionAdapter implements DTOAdapter<Direccion, Direccion>{
	
	private final ModelMapper mapper;
	private final LocalidadService localidadService;
	
	@Override
	public Direccion toDTO(Direccion entity) {
		return entity;
	}

	@Override
	public Direccion toEntity(Direccion dto) {
		var entity = mapper.map(dto, Direccion.class);
		entity.setLocalidad(localidadService.readOne(dto.getLocalidad().getId()));
		return entity;
	}

}
