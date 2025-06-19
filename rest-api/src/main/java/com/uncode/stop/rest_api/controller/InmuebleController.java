package com.uncode.stop.rest_api.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uncode.stop.rest_api.adapter.InmuebleAdapter;
import com.uncode.stop.rest_api.entity.Inmueble;
import com.uncode.stop.rest_api.entity.Localidad;
import com.uncode.stop.rest_api.service.InmuebleService;

@RestController
@RequestMapping("/inmuebles")
public class InmuebleController extends CrudController<Inmueble, UUID, Inmueble> {

	@Autowired
	private InmuebleService service;
	
    public InmuebleController(InmuebleService service, InmuebleAdapter adapter) {
        super(service, adapter);
    }

    @GetMapping("/listarInmueblesPorBarrio/{id}")
    public List<Inmueble> listarInmueblesPorBarrio(@PathVariable UUID id){
    	return service.listarInmueblesPorBarrio(id);
    }
    
}
