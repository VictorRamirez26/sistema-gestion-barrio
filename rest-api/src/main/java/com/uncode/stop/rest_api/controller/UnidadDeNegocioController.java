package com.uncode.stop.rest_api.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.uncode.stop.rest_api.adapter.DTOAdapter;
import com.uncode.stop.rest_api.adapter.IdentityAdapter;
import com.uncode.stop.rest_api.adapter.UnidadDeNegocioAdapter;
import com.uncode.stop.rest_api.entity.UnidadDeNegocio;
import com.uncode.stop.rest_api.service.CrudService;
import com.uncode.stop.rest_api.service.UnidadDeNegocioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/unidades")
public class UnidadDeNegocioController extends CrudController<UnidadDeNegocio, UUID, UnidadDeNegocio>{

	public UnidadDeNegocioController(UnidadDeNegocioService service,
			IdentityAdapter<UnidadDeNegocio> adapter) {
		super(service, adapter);
		// TODO Auto-generated constructor stub
	}

	
	/*
	@Autowired
	private UnidadDeNegocioService service;
	
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UnidadDeNegocio create(@Valid @RequestBody UnidadDeNegocio dto) {
    	
    	var direccion = dto.getDireccion();
    	System.out.println(direccion.getCalle());
    	System.out.println(direccion.getLocalidad().getId());
    	
    	
        return adapter.toDTO(service.create(adapter.toEntity(dto)));
    }
    */
}
