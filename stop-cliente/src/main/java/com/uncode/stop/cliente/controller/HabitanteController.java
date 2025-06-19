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

import com.uncode.stop.cliente.dtos.EmpleadoDTO;
import com.uncode.stop.cliente.dtos.HabitanteDTO;
import com.uncode.stop.cliente.dtos.UnidadNegocioDTO;
import com.uncode.stop.cliente.dtos.UsuarioDTO;
import com.uncode.stop.cliente.enums.Rol;
import com.uncode.stop.cliente.services.HabitanteService;
import com.uncode.stop.cliente.services.UnidadNegocioService;

@Controller
@RequestMapping("/habitante")
public class HabitanteController {

    @Autowired
    private HabitanteService habitanteService;
    
    @Autowired
    private UnidadNegocioService unidadNegocioService;

    // Listar todos los habitantes
    @GetMapping("/listarHabitantes")
    public String listarHabitantes(ModelMap model) {
        List<HabitanteDTO> habitantes = habitanteService.listar();
        model.put("habitantes", habitantes);
        return "/habitante/habitante.html";
    }

    // Editar o crear un habitante
    @GetMapping("/editar-habitante")
    public String editarHabitante(@RequestParam(value = "id", required = false) UUID id,
                                  @RequestParam(value = "accion") String accion,
                                  ModelMap model) {
        
        HabitanteDTO habitante = id == null ? new HabitanteDTO() : habitanteService.buscar(id);
        List<UnidadNegocioDTO> barrios = unidadNegocioService.listar();
        model.put("habitante", habitante);
        model.put("barrios", barrios);
        model.put("accion", accion);
        return "/habitante/editHabitante.html";
    }

    // Actualizar o crear un habitante
    @PostMapping("/actualizar-habitante")
    public String actualizarHabitante(@RequestParam(value = "id", required = false) UUID id,
                                      @RequestParam(value = "nombre") String nombre,
                                      @RequestParam(value = "apellido") String apellido,
                                      @RequestParam(value = "inmuebleId") UUID inmuebleId,
                                      @RequestParam(value = "accion", defaultValue = "Crear") String accion,
                                      ModelMap model) {
        
        if (id == null) {
            habitanteService.crear(nombre, apellido, inmuebleId);
        } else {
            habitanteService.modificar(id, nombre, apellido, inmuebleId);
        }
        return "redirect:/habitante/listarHabitantes";
    }

    // Eliminar un habitante
    @PostMapping("/eliminar-habitante")
    public String eliminarHabitante(@RequestParam("id") UUID id) {
        habitanteService.eliminar(id);
        return "redirect:/habitante/listarHabitantes";
    }
    
    @GetMapping("/editar-usuario")
    public String editUsuario(@RequestParam("id") UUID id, 
    		@RequestParam(value = "accion") String accion,
    		ModelMap model) {

    	HabitanteDTO habitante = habitanteService.buscar(id);
    	
    	if (accion.equals("CrearUsuario")) {
    		habitante.setUsuario(new UsuarioDTO());
		}
    	model.put("habitante", habitante);
    	model.put("accion", accion);
    	model.put("roles", Rol.values());
    	return "/usuario/editUsuarioHabitante.html";
    }
    
    @PostMapping("/actualizar-usuario")
    public String editUsuario(@RequestParam(value = "id" , required = false) UUID id,
    		@RequestParam(value = "accion", required = false) String accion,
    		@RequestParam(value = "cuenta") String cuenta,
    		@RequestParam(value = "clave") String clave,
    		@RequestParam(value = "confirmarClave") String confirmarClave,
    		@RequestParam(value = "rol") Rol rol) {
    	
    	if (accion.equals("CrearUsuario")) {
    		habitanteService.crearUsuario(id, cuenta, clave, confirmarClave, rol);
		}else {
			habitanteService.modificarUsuario(id, cuenta, clave, confirmarClave ,rol);
		}
    	
    	return "redirect:/habitante/listarHabitantes";
    }
}
