package com.uncode.stop.rest_api.service;

import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.uncode.stop.rest_api.dto.UsuarioDTO;
import com.uncode.stop.rest_api.entity.Usuario;
import com.uncode.stop.rest_api.error.ServiceException;
import com.uncode.stop.rest_api.repository.UsuarioRepository;

@Service
public class UsuarioService extends CRUDService2<Usuario, UUID, UsuarioDTO> {

    private final UsuarioRepository repository;
    private final ModelMapper modelMapper;

    public UsuarioService(UsuarioRepository repository, ModelMapper modelMapper) {
        super(repository);
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void validate(Usuario entity) {
        var cuenta = entity.getCuenta();
        if (cuenta == null || cuenta.isBlank()) {
            throw new ServiceException("La cuenta es requerida");
        }

        var clave = entity.getClave();
        if (clave == null || clave.isBlank()) {
            throw new ServiceException("La clave es requerida");
        }

        var rol = entity.getRol();
        if (rol == null) {
            throw new ServiceException("El rol es requerido");
        }

        var existing = repository.findByCuenta(cuenta);
        if (existing.isPresent() && !existing.get().getId().equals(entity.getId())) {
            throw new ServiceException("Ya existe un usuario con esa cuenta");
        }
    }

    @Override
    protected Usuario toEntity(UsuarioDTO dto) {
        if (!dto.getClave().equals(dto.getConfirmarClave())) {
            throw new ServiceException("La confirmación de la clave debe coincidir con la clave");
        }
        var entity = modelMapper.map(dto, Usuario.class);
        return entity;
    }

    @Override
    protected Usuario toEntity(UUID id, UsuarioDTO dto) {
        var entity = repository.findById(id).orElseThrow(() -> new ServiceException("No se encontró el usuario"));
        if (!dto.getClave().equals(dto.getConfirmarClave())) {
            throw new ServiceException("La confirmación de la clave debe coincidir con la clave");
        }
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(dto, entity);
        modelMapper.getConfiguration().setSkipNullEnabled(false);
        return entity;
    }

    public Optional<Usuario> findByCuenta(String cuenta) {
        return repository.findByCuenta(cuenta);
        //return repository.findByCuenta(cuenta).orElseThrow(() -> new ServiceException("Usuario not found"));
    }


}
