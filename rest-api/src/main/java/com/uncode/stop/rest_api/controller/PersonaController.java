package com.uncode.stop.rest_api.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.uncode.stop.rest_api.dto.ContactoDTO;
import com.uncode.stop.rest_api.dto.UsuarioDTO;
import com.uncode.stop.rest_api.entity.Contacto;
import com.uncode.stop.rest_api.entity.Persona;
import com.uncode.stop.rest_api.entity.Usuario;
import com.uncode.stop.rest_api.service.CRUDService2;
import com.uncode.stop.rest_api.service.PersonaService;

import jakarta.validation.Valid;

@RestController
public abstract class PersonaController<E extends Persona, DTO> extends EntityController<E, UUID, DTO> {

    private final PersonaService personaService;

    public PersonaController(CRUDService2<E, UUID, DTO> service, PersonaService personaService) {
        super(service);
        this.personaService = personaService;
    }

    @PostMapping("/{id}/usuario")
    public Usuario createUsuario(@PathVariable UUID id, @Valid @RequestBody UsuarioDTO dto) {
        return personaService.createUsuario(id, dto);
    }

    @GetMapping("/{id}/usuario")
    public Usuario getUsuario(@PathVariable UUID id) {
        return personaService.getUsuario(id);
    }

    @PutMapping("/{id}/usuario")
    public Usuario updateUsuario(@PathVariable UUID id, @Valid @RequestBody UsuarioDTO dto) {
        return personaService.updateUsuario(id, dto);
    }

    @DeleteMapping("/{id}/usuario")
    public void deleteUsuario(@PathVariable UUID id) {
        personaService.deleteUsuario(id);
    }

    @GetMapping("/{id}/contactos")
    public List<Contacto> getContactos(@PathVariable UUID id) {
        return personaService.getContactos(id);
    }

    @GetMapping("/{id}/contactos/{contactoId}")
    public Contacto getContactos(@PathVariable UUID id, @PathVariable UUID contactoId) {
        return personaService.getContacto(id, contactoId);
    }

    @PostMapping("/{id}/contactos")
    public Contacto createContacto(@PathVariable UUID id, @Valid @RequestBody ContactoDTO dto) {
        return personaService.createContacto(id, dto);
    }

    @PutMapping("/{id}/contactos/{contactoId}")
    public Contacto updateContacto(@PathVariable UUID id, @PathVariable UUID contactoId,
            @Valid @RequestBody ContactoDTO dto) {
        return personaService.updateContacto(id, contactoId, dto);
    }

    @DeleteMapping("/{id}/contactos/{contactoId}")
    public void deleteContacto(@PathVariable UUID id, @PathVariable UUID contactoId) {
        personaService.deleteContacto(id, contactoId);
    }

}
