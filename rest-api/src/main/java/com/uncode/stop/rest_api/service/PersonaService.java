package com.uncode.stop.rest_api.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.uncode.stop.rest_api.dto.ContactoDTO;
import com.uncode.stop.rest_api.dto.UsuarioDTO;
import com.uncode.stop.rest_api.entity.Contacto;
import com.uncode.stop.rest_api.entity.Persona;
import com.uncode.stop.rest_api.entity.Usuario;
import com.uncode.stop.rest_api.error.NotFoundException;
import com.uncode.stop.rest_api.error.ServiceException;
import com.uncode.stop.rest_api.repository.PersonaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonaService {

    private final PersonaRepository repository;
    private final UsuarioService usuarioService;
    private final ContactoService contactoService;

    public void validate(Persona entity) {
        var nombre = entity.getNombre();
        if (nombre == null || nombre.isBlank()) {
            throw new ServiceException("El nombre es requerido");
        }

        var apellido = entity.getApellido();
        if (apellido == null || apellido.isBlank()) {
            throw new ServiceException("El apellido es requerido");
        }
    }

    public Usuario createUsuario(UUID id, UsuarioDTO dto) {
        var persona = repository.findById(id).orElseThrow(() -> new NotFoundException("No se encontró la persona"));
        var usuario = usuarioService.create(dto);
        persona.setUsuario(usuario);
        repository.save(persona);
        return usuario;
    }

    public Usuario getUsuario(UUID id) {
        var persona = repository.findById(id).orElseThrow(() -> new NotFoundException("No se encontró la persona"));
        return persona.getUsuario();
    }

    public Usuario updateUsuario(UUID id, UsuarioDTO dto) {
        var persona = repository.findById(id).orElseThrow(() -> new NotFoundException("No se encontró la persona"));
        var usuario = persona.getUsuario();
        usuarioService.update(usuario.getId(), dto);
        return usuario;
    }

    public void deleteUsuario(UUID id) {
        var persona = repository.findById(id).orElseThrow(() -> new NotFoundException("No se encontró la persona"));
        var usuario = persona.getUsuario();
        usuarioService.delete(usuario.getId());
    }

    public List<Contacto> getContactos(UUID id) {
        var persona = repository.findById(id).orElseThrow(() -> new NotFoundException("No se encontró la persona"));
        return persona.getContactos();
    }

    public Contacto getContacto(UUID id, UUID contactoId) {
        var persona = repository.findById(id).orElseThrow(() -> new NotFoundException("No se encontró la persona"));
        return persona.getContactos().stream().filter(c -> c.getId().equals(contactoId)).findFirst()
                .orElseThrow(() -> new NotFoundException("Contacto not found"));
    }

    public Contacto createContacto(UUID id, ContactoDTO dto) {
        var persona = repository.findById(id).orElseThrow(() -> new NotFoundException("No se encontró la persona"));
        var contacto = contactoService.create(dto);
        persona.getContactos().add(contacto);
        repository.save(persona);
        return contacto;
    }

    public Contacto updateContacto(UUID id, UUID contactoId, ContactoDTO dto) {
        var persona = repository.findById(id).orElseThrow(() -> new NotFoundException("No se encontró la persona"));
        var contacto = persona.getContactos().stream().filter(c -> c.getId().equals(contactoId)).findFirst()
                .orElseThrow(() -> new NotFoundException("Contacto not found"));
        contactoService.update(contacto.getId(), dto);
        return contacto;
    }

    public void deleteContacto(UUID id, UUID contactoId) {
        var persona = repository.findById(id).orElseThrow(() -> new NotFoundException("No se encontró la persona"));
        var contacto = persona.getContactos().stream().filter(c -> c.getId().equals(contactoId)).findFirst()
                .orElseThrow(() -> new NotFoundException("Contacto not found"));
        contactoService.delete(contacto.getId());
    }

}
