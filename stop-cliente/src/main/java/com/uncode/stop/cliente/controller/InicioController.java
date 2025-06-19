package com.uncode.stop.cliente.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.uncode.stop.cliente.enums.TipoContacto;
import com.uncode.stop.cliente.enums.TipoEmpleado;
import com.uncode.stop.cliente.enums.TipoTelefono;
import com.uncode.stop.cliente.services.PersonaService;

@Controller
@RequestMapping("/")
public class InicioController {

	@Autowired
	private PersonaService personaService;
	
	@GetMapping("")
	public String inicio(ModelMap model) {
		model.put("tipoContacto", TipoContacto.values());
		model.put("tipoEmpleado", TipoEmpleado.values());
		model.put("tipoTelefono", TipoTelefono.values());
		return "index.html";
	}
	
	@PostMapping("/aceptarPersona")
	public String aceptarPersona(@RequestParam String nombre,
			@RequestParam String apellido, 
			@RequestParam String legajo,
			@RequestParam TipoEmpleado tipoEmpleado,
			@RequestParam TipoContacto tipoContacto,
			@RequestParam String observacion,
			@RequestParam String telefono,
			@RequestParam TipoTelefono tipoTelefono,
			@RequestParam String email) {
		
		//personaService.crear(nombre, apellido, legajo, tipoEmpleado, tipoContacto, observacion, telefono, tipoTelefono, email);
		return "index.html";
	}
	
}
