package com.uncode.stop.rest_api.controller;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uncode.stop.rest_api.dto.EmpleadoDTO;
import com.uncode.stop.rest_api.entity.Empleado;
import com.uncode.stop.rest_api.service.EmpleadoService;
import com.uncode.stop.rest_api.service.PersonaService;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController extends PersonaController<Empleado, EmpleadoDTO> {

    private final ModelMapper modelMapper;

    public EmpleadoController(EmpleadoService service, PersonaService personaService, ModelMapper modelMapper) {
        super(service, personaService);
        this.modelMapper = modelMapper;
    }

}
