package com.uncode.stop.rest_api.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uncode.stop.rest_api.adapter.DireccionAdapter;
import com.uncode.stop.rest_api.entity.Direccion;
import com.uncode.stop.rest_api.service.DireccionService;

@RestController
@RequestMapping("/direcciones")
public class DireccionController extends CrudController<Direccion, UUID, Direccion>{

	public DireccionController(DireccionService service, DireccionAdapter adapter) {
		super(service, adapter);
	}

}
