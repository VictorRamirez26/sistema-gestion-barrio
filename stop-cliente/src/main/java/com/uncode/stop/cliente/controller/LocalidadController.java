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

import com.uncode.stop.cliente.dtos.DepartamentoDTO;
import com.uncode.stop.cliente.dtos.LocalidadDTO;
import com.uncode.stop.cliente.dtos.PaisDTO;
import com.uncode.stop.cliente.dtos.ProvinciaDTO;
import com.uncode.stop.cliente.services.DepartamentoService;
import com.uncode.stop.cliente.services.LocalidadService;
import com.uncode.stop.cliente.services.PaisService;
import com.uncode.stop.cliente.services.ProvinciaService;

@Controller
@RequestMapping("/localidad")
public class LocalidadController {
	
	@Autowired
	private LocalidadService localidadService;

	@Autowired
	private DepartamentoService departamentoService;
	
	@Autowired
	private ProvinciaService provinciaService;
	
	@Autowired
	private PaisService paisService;
	
	
	@GetMapping("/listarLocalidades")
	public String iniciarLocalidades(ModelMap model) {
		List<LocalidadDTO> localidades = localidadService.listar();
		model.put("localidades", localidades);
		return "/localidad/localidad.html";
	}
	
	@GetMapping("/editar-localidad")
	public String editDepartamento(@RequestParam(value = "id", required = false) UUID id,
			@RequestParam(value = "accion") String accion , 
			ModelMap model) {
		
		LocalidadDTO localidad = null;
		List<PaisDTO> paises = paisService.listar();
		
		List<ProvinciaDTO> provincias;
		List<DepartamentoDTO> departamentos;
		
		if (id == null) {
			localidad = new LocalidadDTO();
			provincias = provinciaService.listar();
			departamentos = departamentoService.listar();
			
		}else {
			localidad = localidadService.buscar(id);
			provincias = provinciaService.listarProvinciasPorPais(localidad.getDepartamento().getProvincia().getPais().getId());
			departamentos = departamentoService.listarDepartamentosPorProvincia(localidad.getDepartamento().getProvincia().getId());
			
		}
		model.put("localidad", localidad);
		model.put("paises", paises);
		model.put("provincias", provincias);
		model.put("departamentos", departamentos);
		model.put("accion", accion);
		return "/localidad/editLocalidad.html";
	}
	
    @PostMapping("/actualizar-localidad")
    public String actualizarLocalidad(@RequestParam(value = "id" , required = false) UUID id,
    		@RequestParam(value = "nombre") String nombre,
    		@RequestParam(value = "codigoPostal") String codigoPostal,
    		@RequestParam(value = "departamentoId") UUID departamentoId,
    		@RequestParam(value = "accion" , defaultValue = "Crear") String accion, 
    		ModelMap model) {
    	
        if (id == null) {
        	localidadService.crear(id, nombre, codigoPostal, departamentoId);
        } else {
        	localidadService.modificar(id, nombre, codigoPostal, departamentoId);
        }
        return "redirect:/localidad/listarLocalidades";
    }

    @PostMapping("/eliminar-localidad")
    public String eliminarLocalidad(@RequestParam("id") UUID id) {
    	localidadService.eliminar(id);
        return "redirect:/localidad/listarLocalidades";
    }
    
    @GetMapping("/listarLocalidadesPorDepartamento/{id}")
    public ResponseEntity<List<LocalidadDTO>> listarLocalidades(@PathVariable("id") UUID idDepartamento) {
        List<LocalidadDTO> localidades = localidadService.listarLocalidadesPorDepartamento(idDepartamento);
        return ResponseEntity.ok(localidades);  // Devolver departamentos en formato JSON
    }
    
}
