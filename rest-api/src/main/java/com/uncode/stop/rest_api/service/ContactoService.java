package com.uncode.stop.rest_api.service;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.uncode.stop.rest_api.dto.ContactoDTO;
import com.uncode.stop.rest_api.entity.Contacto;
import com.uncode.stop.rest_api.entity.ContactoCorreoElectronico;
import com.uncode.stop.rest_api.entity.ContactoTelefonico;
import com.uncode.stop.rest_api.error.ServiceException;
import com.uncode.stop.rest_api.repository.ContactoCorreoElectronicoRepository;
import com.uncode.stop.rest_api.repository.ContactoRepository;

@Service
public class ContactoService extends CRUDService2<Contacto, UUID, ContactoDTO> {

    private final ContactoRepository repository;
    private final ModelMapper modelMapper;
    private final ContactoCorreoElectronicoRepository contactoCorreoElectronicoRepository;

    public ContactoService(ContactoRepository repository, ModelMapper modelMapper,
            ContactoCorreoElectronicoRepository contactoCorreoElectronicoRepository) {
        super(repository);
        this.repository = repository;
        this.modelMapper = modelMapper;
        this.contactoCorreoElectronicoRepository = contactoCorreoElectronicoRepository;
    }

    // https://emailregex.com/
    private final String EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

    @Override
    protected void validate(Contacto entity) {
        if (entity.getTipoContacto() == null) {
            throw new ServiceException("El tipo de contacto es requerido");
        }

        if (entity instanceof ContactoTelefonico) {
            var telefono = ((ContactoTelefonico) entity).getTelefono();
            if (telefono == null || telefono.isBlank()) {
                throw new ServiceException("El telefono es requerido");
            }

            var tipoTelefono = ((ContactoTelefonico) entity).getTipoTelefono();
            if (tipoTelefono == null) {
                throw new ServiceException("El tipo de telefono es requerido");
            }
        } else if (entity instanceof ContactoCorreoElectronico) {
            var email = ((ContactoCorreoElectronico) entity).getEmail();
            if (email == null || email.isBlank()) {
                throw new ServiceException("El email es requerido");
            }

            if (!email.matches(EMAIL_REGEX)) {
                throw new ServiceException("El email no es válido");
            }

            var existing = contactoCorreoElectronicoRepository.findByEmail(email);
            if (existing.isPresent() && !existing.get().getId().equals(entity.getId())) {
                throw new ServiceException("El email ya está en uso");
            }

        }
    }

    @Override
    protected Contacto toEntity(ContactoDTO dto) {
        if (dto.getEmail() != null) {
            return modelMapper.map(dto, ContactoCorreoElectronico.class);
        } else if (dto.getTelefono() != null) {
            return modelMapper.map(dto, ContactoTelefonico.class);
        } else {
            throw new ServiceException("El contacto debe tener un email o un telefono");
        }
    }

    @Override
    protected Contacto toEntity(UUID id, ContactoDTO dto) {
        var entity = repository.findById(id).orElseThrow(() -> new ServiceException("No se encontró el contacto"));
        modelMapper.map(dto, entity);
        return entity;
    }

}
