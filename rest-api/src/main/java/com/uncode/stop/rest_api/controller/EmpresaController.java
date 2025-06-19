package com.uncode.stop.rest_api.controller;

import java.util.UUID;

import com.uncode.stop.rest_api.dto.EmpresaDTO;
import com.uncode.stop.rest_api.entity.Empresa;
import com.uncode.stop.rest_api.service.EmpresaService;

public class EmpresaController extends EntityController<Empresa, UUID, EmpresaDTO> {

    public EmpresaController(EmpresaService service) {
        super(service);
    }
}
