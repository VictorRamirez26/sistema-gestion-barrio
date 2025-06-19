package com.uncode.stop.rest_api.adapter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.uncode.stop.rest_api.entity.UnidadDeNegocio;
import com.uncode.stop.rest_api.service.DireccionService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UnidadDeNegocioAdapter implements DTOAdapter<UnidadDeNegocio, UnidadDeNegocio>{
	
	private final ModelMapper mapper;
	private final DireccionService direccionService;

	@Override
	public UnidadDeNegocio toDTO(UnidadDeNegocio entity) {
		return entity;
	}

	@Override
	public UnidadDeNegocio toEntity(UnidadDeNegocio dto) {
		var entity = mapper.map(dto, UnidadDeNegocio.class);
		entity.setDireccion(direccionService.readOne(dto.getDireccion().getId()));
		return entity;
	}
	
	
}
