package com.uncode.stop.rest_api.service;

import com.uncode.stop.rest_api.entity.Servicio;
import com.uncode.stop.rest_api.error.ServiceException;
import com.uncode.stop.rest_api.repository.ServicioRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ServicioService extends CrudService<Servicio, UUID> {

    private final ImagenService imagenService;

    public ServicioService(ServicioRepository repository, ImagenService imagenService) {
        super(repository);
        this.imagenService = imagenService;
    }

    @Override
    public void validate(Servicio entity) {
        var nombre = entity.getNombre();

        if (nombre == null || nombre.isBlank()) {
            throw new ServiceException("El nombre es requerido");
        }

        var imagen = entity.getImagen();

        if (imagen == null) {
            throw new ServiceException("La imagen es requerida");
        }

    }

}
