package com.uncode.stop.rest_api.controller;

import com.uncode.stop.rest_api.adapter.IdentityAdapter;
import com.uncode.stop.rest_api.dto.ServicioDTO;
import com.uncode.stop.rest_api.entity.Imagen;
import com.uncode.stop.rest_api.entity.Servicio;
import com.uncode.stop.rest_api.repository.ImagenRepository;
import com.uncode.stop.rest_api.repository.ServicioRepository;
import com.uncode.stop.rest_api.service.ImagenService;
import com.uncode.stop.rest_api.service.ServicioService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/servicio")
public class ServicioController extends CrudController<Servicio, UUID, Servicio> {

    private final ImagenService imagenService;
    private final ServicioRepository repository;

    public ServicioController(ServicioService service, IdentityAdapter<Servicio> adapter,
                              ServicioRepository repository, ImagenService imagenService) {
        super(service, adapter);
        this.imagenService = imagenService;
        this.repository = repository;
    }

    @PostMapping(value = "/crear", consumes = "multipart/form-data")
    public ResponseEntity<Servicio> crearServicio(
            @RequestParam("nombre") String nombre,
            @RequestParam("imagen") MultipartFile imagenFile) throws IOException {
        Servicio servicio = new Servicio();
        servicio.setNombre(nombre);

        Imagen imagen = imagenService.guardarImagen(imagenFile);
        servicio.setImagen(imagen);

        repository.save(servicio);
        return ResponseEntity.ok(servicio);
    }

    @GetMapping(value = "/obtener")
    public ResponseEntity<List<ServicioDTO>> obtenerServicios() {
        List<Servicio> servicios = repository.findAll();
        List<ServicioDTO> servicioDTOs = servicios.stream()
                .map(servicio -> {
                    ServicioDTO dto = new ServicioDTO();
                    dto.setId(servicio.getId());
                    dto.setNombre(servicio.getNombre());
                    if (servicio.getImagen() != null) {
                        dto.setImagenId(servicio.getImagen().getId());
                    }
                    return dto;
                })
                .toList();
        return ResponseEntity.ok(servicioDTOs);
    }

}