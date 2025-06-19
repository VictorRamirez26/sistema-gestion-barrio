package com.uncode.stop.rest_api.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uncode.stop.rest_api.adapter.DepartamentoAdapter;
import com.uncode.stop.rest_api.entity.Departamento;
import com.uncode.stop.rest_api.service.DepartamentoService;

@RestController
@RequestMapping("/departamentos")
public class DepartamentoController extends CrudController<Departamento, UUID, Departamento>{

	@Autowired
	private DepartamentoService service;
	
	public DepartamentoController(DepartamentoService service, DepartamentoAdapter adapter) {
		super(service, adapter);
	}

    @GetMapping("/listarDepartamentosPorProvincia/{id}")
    public List<Departamento> listarDepartamentosPorProvincia(@PathVariable UUID id){
    	return service.listarDepartamentosPorProvincia(id);
    }
}
