package com.uncode.stop.rest_api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uncode.stop.rest_api.dto.HabitanteDTO;
import com.uncode.stop.rest_api.entity.Habitante;
import com.uncode.stop.rest_api.service.HabitanteService;
import com.uncode.stop.rest_api.service.PersonaService;

@RestController
@RequestMapping("/habitantes")
public class HabitanteController extends PersonaController<Habitante, HabitanteDTO> {

    public HabitanteController(HabitanteService service, PersonaService personaService) {
        super(service, personaService);
    }
}
