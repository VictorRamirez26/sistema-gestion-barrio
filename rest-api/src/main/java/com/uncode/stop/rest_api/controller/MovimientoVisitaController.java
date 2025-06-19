package com.uncode.stop.rest_api.controller;

import com.uncode.stop.rest_api.adapter.MovimientoVisitaAdapter;
import com.uncode.stop.rest_api.entity.MovimientoVisita;
import com.uncode.stop.rest_api.service.MovimientoVisitaService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/visita")
public class MovimientoVisitaController extends CrudController<MovimientoVisita, UUID, MovimientoVisita> {

    public MovimientoVisitaController(MovimientoVisitaService service, MovimientoVisitaAdapter adapter) {
        super(service, adapter);
    }

}
