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
import com.uncode.stop.cliente.dtos.PaisDTO;
import com.uncode.stop.cliente.dtos.ProvinciaDTO;
import com.uncode.stop.cliente.services.DepartamentoService;
import com.uncode.stop.cliente.services.PaisService;
import com.uncode.stop.cliente.services.ProvinciaService;

@Controller
@RequestMapping("/departamento")
public class DepartamentoController {
	@Autowired
	private DepartamentoService departamentoService;
	
	@Autowired
	private ProvinciaService provinciaService;
	
	@Autowired
	private PaisService paisService;
	
	@GetMapping("/listarDepartamentos")
	public String iniciarDepartamentos(ModelMap model) {
		List<DepartamentoDTO> departamentos = departamentoService.listar();
		model.put("departamentos", departamentos);
		return "/departamento/departamento.html";
	}
	
	@GetMapping("/editar-departamento")
	public String editDepartamento(@RequestParam(value = "id", required = false) UUID id,
			@RequestParam(value = "accion") String accion , 
			ModelMap model) {
		
		DepartamentoDTO departamento = null;
		List<PaisDTO> paises = paisService.listar();
		
		List<ProvinciaDTO> provincias;
		
		if (id == null) {
			departamento = new DepartamentoDTO();
			 provincias = provinciaService.listar();
		}else {
			departamento = departamentoService.buscar(id);
			provincias = provinciaService.listarProvinciasPorPais(departamento.getProvincia().getPais().getId());
		}
		model.put("departamento", departamento);
		model.put("paises", paises);
		model.put("provincias", provincias);
		model.put("accion", accion);
		return "/departamento/editDepartamento.html";
	}
	
    @PostMapping("/actualizar-departamento")
    public String actualizarDepartamento(@RequestParam(value = "id" , required = false) UUID id,
    		@RequestParam(value = "nombre") String nombre,
    		@RequestParam(value = "provinciaId") UUID provinciaId,
    		@RequestParam(value = "accion" , defaultValue = "Crear") String accion, 
    		ModelMap model) {
    	
        if (id == null) {
        	departamentoService.crear(id, nombre, provinciaId);
        } else {
        	departamentoService.modificar(id, nombre, provinciaId);
        }
        return "redirect:/departamento/listarDepartamentos";
    }

    @PostMapping("/eliminar-departamento")
    public String eliminarDepartamento(@RequestParam("id") UUID id) {
    	departamentoService.eliminar(id);
        return "redirect:/departamento/listarDepartamentos";
    }
	
    @GetMapping("/listarDepartamentosPorProvincia/{id}")
    public ResponseEntity<List<DepartamentoDTO>> listarDepartamentos(@PathVariable("id") UUID idProvincia) {
        List<DepartamentoDTO> departamentos = departamentoService.listarDepartamentosPorProvincia(idProvincia);
        return ResponseEntity.ok(departamentos);  // Devolver departamentos en formato JSON
    }
	
}
