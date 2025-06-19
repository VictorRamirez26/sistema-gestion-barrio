package com.uncode.stop.rest_api.adapter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.uncode.stop.rest_api.entity.Departamento;
import com.uncode.stop.rest_api.service.ProvinciaService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DepartamentoAdapter implements DTOAdapter<Departamento, Departamento>{
	
	private final ModelMapper mapper;
	
	private final ProvinciaService provinciaService;
	
	@Override
	public Departamento toDTO(Departamento entity) {
		return entity;
	}

	@Override
	public Departamento toEntity(Departamento dto) {
		var entity = mapper.map(dto, Departamento.class);
		entity.setProvincia(provinciaService.readOne(dto.getProvincia().getId()));
		return entity;
	}

	
}
