package com.uncode.stop.cliente.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.uncode.stop.cliente.dtos.PaisDTO;
import com.uncode.stop.cliente.dtos.ProvinciaDTO;
import com.uncode.stop.cliente.services.PaisService;
import com.uncode.stop.cliente.services.ProvinciaService;


@Controller
@RequestMapping("/provincia")
public class ProvinciaController {
	@Autowired
	private ProvinciaService provinciaService;
	
	@Autowired
	private PaisService paisService;
	
	@GetMapping("/listarProvincias")
	public String iniciarProvincias(ModelMap model) {
		List<ProvinciaDTO> provincias = provinciaService.listar();
		model.put("provincias", provincias);
		return "/provincia/provincia.html";
	}
	
	@GetMapping("/editar-provincia")
	public String editProvincia(@RequestParam(value = "id", required = false) UUID id ,
			@RequestParam(value = "accion") String accion , ModelMap model) {
		
		ProvinciaDTO provincia = null;
		List<PaisDTO> paises = paisService.listar();
		
		if (id == null) {
			provincia = new ProvinciaDTO();
		}else {
			provincia = provinciaService.buscar(id);
		}
		model.put("provincia", provincia);
		model.put("paises", paises);
		model.put("accion", accion);
		return "/provincia/editProvincia.html";
	}
	
    @PostMapping("/actualizar-provincia")
    public String actualizarProvincia(@RequestParam(value = "id" , required = false) UUID id,
    		@RequestParam(value = "nombre") String nombre,
    		@RequestParam(value = "paisId") UUID paisId,
    		@RequestParam(value = "accion" , defaultValue = "Crear") String accion, 
    		ModelMap model) {
    	
        if (id == null) {
        	provinciaService.crear(id, nombre , paisId);
        } else {
        	provinciaService.modificar(id,nombre, paisId);
        }
        return "redirect:/provincia/listarProvincias";
    }

    @PostMapping("/eliminar-provincia")
    public String eliminarPais(@RequestParam("id") UUID id) {
        provinciaService.eliminar(id);
        return "redirect:/provincia/listarProvincias";
    }
    
    
    @GetMapping("/listarProvinciasPorPais/{id}")
    public ResponseEntity<List<ProvinciaDTO>> listarProvincias(@PathVariable("id") UUID idPais) {
        List<ProvinciaDTO> provincias = provinciaService.listarProvinciasPorPais(idPais);
        return ResponseEntity.ok(provincias);  // Devolver provincias en formato JSON
    }

}
