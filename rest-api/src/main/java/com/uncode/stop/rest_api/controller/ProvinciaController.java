package com.uncode.stop.rest_api.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uncode.stop.rest_api.adapter.ProvinciaAdapter;
import com.uncode.stop.rest_api.entity.Pais;
import com.uncode.stop.rest_api.entity.Provincia;
import com.uncode.stop.rest_api.service.ProvinciaService;

@RestController
@RequestMapping("/provincias")
public class ProvinciaController extends CrudController<Provincia, UUID, Provincia> {
	
	@Autowired
	private ProvinciaService service;

    public ProvinciaController(ProvinciaService service, ProvinciaAdapter adapter) {
        super(service, adapter);
    }

    @GetMapping("/listarProvinciasPorPais/{id}")
    public List<Provincia> listarProvinciaPorPais(@PathVariable UUID id){
    	return service.listarProvinciasPorPais(id);
    }
}
