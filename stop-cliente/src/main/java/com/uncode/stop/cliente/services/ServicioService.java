package com.uncode.stop.cliente.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.uncode.stop.cliente.dtos.ServicioDTO;
import com.uncode.stop.cliente.rest.ServicioDAORest;

@Service
public class ServicioService {

	@Autowired
	private ServicioDAORest dao;

	public void crear(String nombre, MultipartFile imagen) {
		try {
			dao.crear(
					nombre,
					imagen.getBytes(),
					imagen.getOriginalFilename(),
					imagen.getContentType()
			);
		} catch (Exception e) {
			throw new RuntimeException("Error al crear el servicio", e);
		}
	}

	// Versión alternativa si prefieres manejar los bytes directamente
	public void crear(String nombre, byte[] imagenBytes, String nombreImagen, String mimeType) {
		dao.crear(nombre, imagenBytes, nombreImagen, mimeType);
	}

	public void modificar(UUID id, String nombre) {
		ServicioDTO servicio = ServicioDTO.builder()
				.id(id)
				.nombre(nombre)
				.build();
		dao.modificar(servicio);
	}

	public void modificarConImagen(UUID id, String nombre, MultipartFile imagen) {
		try {
			dao.crear(
					nombre,
					imagen.getBytes(),
					imagen.getOriginalFilename(),
					imagen.getContentType()
			);
		} catch (Exception e) {
			throw new RuntimeException("Error al modificar el servicio con imagen", e);
		}
	}

	public ServicioDTO buscar(UUID id) {
		return dao.buscar(id);
	}

	public void eliminar(UUID id) {
		dao.eliminar(id);
	}

	public List<ServicioDTO> listar() {
		return dao.listar();
	}
}