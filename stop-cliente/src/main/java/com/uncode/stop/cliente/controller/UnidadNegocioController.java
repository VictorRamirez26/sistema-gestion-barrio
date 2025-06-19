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
import com.uncode.stop.cliente.dtos.InmuebleDTO;
import com.uncode.stop.cliente.dtos.LocalidadDTO;
import com.uncode.stop.cliente.dtos.PaisDTO;
import com.uncode.stop.cliente.dtos.ProvinciaDTO;
import com.uncode.stop.cliente.dtos.ServicioDTO;
import com.uncode.stop.cliente.dtos.UnidadNegocioDTO;
import com.uncode.stop.cliente.services.DepartamentoService;
import com.uncode.stop.cliente.services.InmuebleService;
import com.uncode.stop.cliente.services.LocalidadService;
import com.uncode.stop.cliente.services.PaisService;
import com.uncode.stop.cliente.services.ProvinciaService;
import com.uncode.stop.cliente.services.ServicioService;
import com.uncode.stop.cliente.services.UnidadNegocioService;

@Controller
@RequestMapping("/unidadNegocio")
public class UnidadNegocioController {
	
	@Autowired
	private UnidadNegocioService unidadNegocioService;
	
	@Autowired
	private LocalidadService localidadService;

	@Autowired
	private DepartamentoService departamentoService;
	
	@Autowired
	private ProvinciaService provinciaService;
	
	@Autowired
	private PaisService paisService;
	
	@Autowired
	private ServicioService servicioService; 
	
	@GetMapping("/listarUnidadNegocio")
	public String iniciarUnidades(ModelMap model) {
		List<UnidadNegocioDTO> unidades = unidadNegocioService.listar();
		model.put("unidades", unidades);
		return "/unidadNegocio/unidadNegocio.html";
	}
	
	@GetMapping("/editar-unidad")
	public String editUnidad(@RequestParam(value = "id", required = false) UUID id,
			@RequestParam(value = "accion" , required = false) String accion , 
			ModelMap model) {
		
		UnidadNegocioDTO unidad = null;
		List<ServicioDTO> servicios = servicioService.listar();
		List<PaisDTO> paises = paisService.listar();
		List<ProvinciaDTO> provincias;
		List<DepartamentoDTO> departamentos;
		List<LocalidadDTO> localidades;
		
		if (id == null) {
			unidad = new UnidadNegocioDTO();
			provincias = provinciaService.listar();
			departamentos = departamentoService.listar();
			localidades = localidadService.listar();
			
		}else {
			unidad = unidadNegocioService.buscar(id);
			provincias = provinciaService.listarProvinciasPorPais(unidad.getDireccion().getLocalidad().getDepartamento().
					getProvincia().getPais().getId());
			
			departamentos = departamentoService.listarDepartamentosPorProvincia(unidad.getDireccion().getLocalidad().
					getDepartamento().getProvincia().getId());
			
			localidades = localidadService.listarLocalidadesPorDepartamento(unidad.getDireccion().getLocalidad().getDepartamento().getId());
			
		}
		
		model.put("unidad", unidad);
		model.put("servicios", servicios);
		model.put("localidades", localidades);
		model.put("paises", paises);
		model.put("provincias", provincias);
		model.put("departamentos", departamentos);
		model.put("accion", accion);
		return "/unidadNegocio/editUnidadNegocio.html";
	}
	

    @PostMapping("/actualizar-unidad")
    public String actualizarUnidad(@RequestParam(value = "id" , required = false) UUID id,
    		@RequestParam(value = "nombre") String nombre,
    		@RequestParam(value = "servicioId") UUID servicioId,
    		@RequestParam(value = "calle") String calle,
    		@RequestParam(value = "numeracion") String numeracion,
    		@RequestParam(value = "latitud") String latitud,
    		@RequestParam(value = "longitud") String longitud,
    		@RequestParam(value = "localidadId") UUID localidadId,
    		@RequestParam(value = "accion" , defaultValue = "Crear") String accion, 
    		ModelMap model) {
    	
        if (id == null) {
        	unidadNegocioService.crear(id, nombre, calle, numeracion, latitud, longitud, localidadId, servicioId);
        } else {
        	unidadNegocioService.modificar(id, nombre, calle, numeracion, latitud, longitud, localidadId, servicioId);
        }
        
        return "redirect:/unidadNegocio/listarUnidadNegocio";
    }

    @PostMapping("/eliminar-unidad")
    public String eliminarUnidad(@RequestParam("id") UUID id) {
    	unidadNegocioService.eliminar(id);
    	return "redirect:/unidadNegocio/listarUnidadNegocio";
    }
    
}
