package com.uncode.stop.rest_api.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uncode.stop.rest_api.entity.Usuario;
import com.uncode.stop.rest_api.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;

    @GetMapping("/{cuenta}")
    public Optional<Usuario> getUsuario(@PathVariable String cuenta) {
        return service.findByCuenta(cuenta);
    }

}
