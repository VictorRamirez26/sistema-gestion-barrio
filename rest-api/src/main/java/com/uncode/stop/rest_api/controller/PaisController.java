package com.uncode.stop.rest_api.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uncode.stop.rest_api.adapter.IdentityAdapter;
import com.uncode.stop.rest_api.entity.Pais;
import com.uncode.stop.rest_api.service.PaisService;

@RestController
@RequestMapping("/paises")
public class PaisController extends CrudController<Pais, UUID, Pais> {

    public PaisController(PaisService service, IdentityAdapter<Pais> adapter) {
        super(service, adapter);
    }
}
