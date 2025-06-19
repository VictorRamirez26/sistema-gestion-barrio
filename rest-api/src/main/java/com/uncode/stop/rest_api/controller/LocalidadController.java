package com.uncode.stop.rest_api.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uncode.stop.rest_api.adapter.LocalidadAdapter;
import com.uncode.stop.rest_api.entity.Localidad;
import com.uncode.stop.rest_api.service.LocalidadService;

@RestController
@RequestMapping("/localidades")
public class LocalidadController extends CrudController<Localidad, UUID, Localidad>{

	@Autowired
	private LocalidadService service;
	
	public LocalidadController(LocalidadService service, LocalidadAdapter adapter) {
		super(service, adapter);
	}

    @GetMapping("/listarLocalidadesPorDepartamento/{id}")
    public List<Localidad> listarLocalidadesPorDepartamento(@PathVariable UUID id){
    	return service.listarLocalidadesPorDepartamento(id);
    }
}
