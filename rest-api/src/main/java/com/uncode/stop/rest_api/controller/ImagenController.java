package com.uncode.stop.rest_api.controller;

import com.uncode.stop.rest_api.adapter.IdentityAdapter;
import com.uncode.stop.rest_api.entity.Imagen;
import com.uncode.stop.rest_api.repository.ImagenRepository;
import com.uncode.stop.rest_api.service.ImagenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.awt.*;
import java.util.UUID;

@RestController
@RequestMapping("/imagen")
public class ImagenController extends CrudController<Imagen, UUID, Imagen> {

    @Autowired
    private RestTemplate restTemplate;

    private final ImagenRepository repository;

    public ImagenController(ImagenService service, IdentityAdapter<Imagen> adapter,
                            ImagenRepository repository) {
        super(service, adapter);
        this.repository = repository;
    }

    @GetMapping("/obtener/{id}")
    public ResponseEntity<byte[]> obtenerImagen(@PathVariable("id") String id) {
        String url = "http://localhost:9000/imagen/" + id;

        // Obtener la imagen como respuesta JSON
        Imagen imagen = restTemplate.getForObject(url, Imagen.class);

        // Devolver el contenido de la imagen como byte[]
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(imagen.getMime()))
                .body(imagen.getContenido());
    }


}
