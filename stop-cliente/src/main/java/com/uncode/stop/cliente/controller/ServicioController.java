package com.uncode.stop.cliente.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.uncode.stop.cliente.dtos.ServicioDTO;
import com.uncode.stop.cliente.services.ServicioService;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/servicio")
public class ServicioController {

	@Autowired
	private ServicioService servicioService;

	@GetMapping("/listarServicios")
	public String iniciarServicios(ModelMap model) {
		List<ServicioDTO> servicios = servicioService.listar();
		model.put("servicios", servicios);
		return "/servicio/servicio.html";
	}

	@GetMapping("/editar-servicio")
	public String editServicio(@RequestParam(value = "id", required = false) UUID id,
							   @RequestParam(value = "accion") String accion, ModelMap model) {

		ServicioDTO servicio = null;
		if (id == null) {
			servicio = new ServicioDTO();
		} else {
			servicio = servicioService.buscar(id);
		}
		model.put("servicio", servicio);
		model.put("accion", accion);
		return "/servicio/editServicio.html";
	}

	@PostMapping("/actualizar-servicio")
	public String actualizarServicio(
			@RequestParam(value = "id", required = false) UUID id,
			@RequestParam("nombre") String nombre,
			@RequestParam(value = "imagen", required = false) MultipartFile imagen,
			@RequestParam(value = "accion", defaultValue = "Crear") String accion,
			ModelMap model) {

		try {
			if (id == null) {
				servicioService.crear(nombre, imagen);
			} else {
				if (imagen != null && !imagen.isEmpty()) {
					// Si se proporcionó una nueva imagen, actualizar con imagen
					servicioService.modificarConImagen(id, nombre, imagen);
				} else {
					// Si no se proporcionó imagen, solo actualizar el nombre
					servicioService.modificar(id, nombre);
				}
			}
			return "redirect:/servicio/listarServicios";
		} catch (Exception e) {
			model.put("error", "Error al procesar el servicio: " + e.getMessage());
			return editServicio(id, accion, model);
		}
	}

	@PostMapping("/eliminar-servicio")
	public String eliminarServicio(@RequestParam("id") UUID id) {
		servicioService.eliminar(id);
		return "redirect:/servicio/listarServicios";
	}
}