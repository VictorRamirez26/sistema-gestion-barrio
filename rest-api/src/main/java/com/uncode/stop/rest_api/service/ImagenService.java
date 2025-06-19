package com.uncode.stop.rest_api.service;

import com.uncode.stop.rest_api.entity.Imagen;
import com.uncode.stop.rest_api.error.ServiceException;
import com.uncode.stop.rest_api.repository.ImagenRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class ImagenService extends CrudService<Imagen, UUID> {

    public final ImagenRepository repository;

    public ImagenService(ImagenRepository repository, ModelMapper mapper){
        super(repository);
        this.repository = repository;
    }

    @Override
    public void validate(Imagen entity) {
        var nombre = entity.getNombre();

        if (nombre == null || nombre.isBlank()) {
            throw new ServiceException("El nombre es requerido");
        }

        var mime = entity.getMime();

        if (mime == null || mime.isBlank()) {
            throw new ServiceException("El mime es requerido");
        }

        var contenido = entity.getContenido();

        if (contenido == null) {
            throw new ServiceException("El contenido es requerido");
        }
    }


    public Imagen guardarImagen(MultipartFile file) throws IOException {
        Imagen imagen = new Imagen();
        imagen.setNombre(file.getOriginalFilename());
        imagen.setMime(file.getContentType());
        imagen.setContenido(file.getBytes());

        return repository.save(imagen);
    }

}