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

import com.uncode.stop.cliente.dtos.PaisDTO;
import com.uncode.stop.cliente.services.PaisService;

@Controller
@RequestMapping("/pais")
public class PaisController {

	@Autowired
	private PaisService paisService;
	
	@GetMapping("/listarPaises")
	public String iniciarPaises(ModelMap model) {
		List<PaisDTO> paises = paisService.listar();
		model.put("paises", paises);
		return "/pais/pais.html";
	}
	
	@GetMapping("/editar-pais")
	public String editPais(@RequestParam(value = "id", required = false) UUID id ,
			@RequestParam(value = "accion") String accion , ModelMap model) {
		
		PaisDTO pais = null;
		if (id == null) {
			 pais = new PaisDTO();
		}else {
			 pais = paisService.buscar(id);
		}
		model.put("pais", pais);
		model.put("accion", accion);
		return "/pais/editPais.html";
	}
	
    @PostMapping("/actualizar-pais")
    public String actualizarPais(@RequestParam(value = "id", required = false) UUID id, @RequestParam("nombre") String nombre, 
    		@RequestParam(value = "accion" , defaultValue = "Crear") String accion, ModelMap model) {
    	
        if (id == null) {
        	paisService.crear(nombre);
        } else {
        	paisService.modificar(id, nombre);
        }
        return "redirect:/pais/listarPaises";
    }

    @PostMapping("/eliminar-pais")
    public String eliminarPais(@RequestParam("id") UUID id) {
        paisService.eliminar(id);
        return "redirect:/pais/listarPaises";
    }
	
}
