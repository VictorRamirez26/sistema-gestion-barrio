package com.uncode.stop.cliente.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.uncode.stop.cliente.dtos.ContactoDTO;
import com.uncode.stop.cliente.dtos.PersonaDTO;
import com.uncode.stop.cliente.dtos.UsuarioDTO;
import com.uncode.stop.cliente.enums.TipoContacto;
import com.uncode.stop.cliente.enums.TipoTelefono;
import com.uncode.stop.cliente.services.PersonaService;

@Controller
@RequestMapping("/contacto")
public class ContactoController {
	
	@Autowired 
	private PersonaService personaService;
	
    @GetMapping("/listar")
    public String iniciarTelefonos(ModelMap model) {
        
        List<PersonaDTO> personas = personaService.listarPersonas();
        // Pasar la lista combinada de personas al modelo
        
        model.put("personas", personas);
        return "/contacto/contacto.html";
    }
    
    @GetMapping("/consultar-telefonos")
    public String consultarTelefonos(@RequestParam(value = "id") UUID id, ModelMap model) {
    	
    		PersonaDTO persona = personaService.buscarPersona(id);
    		if (persona.getContactos().isEmpty()) {
				persona.setContactos(new ArrayList<ContactoDTO>());
			}
    		model.put("persona", persona);
    		model.put("contactos", persona.getContactos());
    		return "/contacto/consultarTelefonos.html";
    }
	
    @GetMapping("/editar-telefono")
	public String editTelefono(@RequestParam(value = "id") UUID id,
			@RequestParam(value = "accion") String accion , 
			@RequestParam(value = "contactoId", required = false) UUID contactoId,
			@RequestParam(value = "tipoPersona") String tipoPersona,
			ModelMap model) {
    	
		PersonaDTO persona = personaService.buscarPersona(id);

		if (persona.getContactos().isEmpty()) {
			persona.setContactos(new ArrayList<ContactoDTO>());
		}
		
		ContactoDTO contacto = new ContactoDTO();
		
		if (contactoId != null) {
			 contacto = personaService.buscarContacto(id, tipoPersona, contactoId);
		}
		
		model.put("tipoContacto", TipoContacto.values());
		model.put("tipoTelefono", TipoTelefono.values());
		model.put("idPersona", id);
		model.put("accion", accion);
		model.put("contacto", contacto);
		model.put("tipoPersona", tipoPersona);
		
		return "/contacto/editTelefono.html";
	}
	
    @PostMapping("/actualizar-telefono")
    public String actualizarTelefono(
            @RequestParam(value = "id") UUID idPersona,
            @RequestParam(value = "contactoId", required = false) UUID contactoId,
            @RequestParam(value = "tipoPersona") String tipoPersona,
            @RequestParam(value = "tipoContacto") TipoContacto tipoContacto,
            @RequestParam(value = "tipoTelefono") TipoTelefono tipoTelefono,
            @RequestParam(value = "observacion") String observacion,
            @RequestParam(value = "telefono") String telefono,
            @RequestParam(value = "accion") String accion, 
            ModelMap model) {

        if (accion.equals("Crear")) {
            personaService.crearTelefono(idPersona, tipoPersona, tipoContacto, tipoTelefono, observacion, telefono);
        } else {
            personaService.modificarTelefono(idPersona, contactoId, tipoPersona, tipoContacto, tipoTelefono, observacion, telefono);
        }

        // Redirigir pasando idPersona como parámetro
        return "redirect:/contacto/consultar-telefonos?id=" + idPersona;
    }

    @PostMapping("/eliminar-telefono")
    public String eliminarTelefono(@RequestParam("id") UUID idPersona,
    		@RequestParam("contactoId") UUID contactoId,
    		@RequestParam("tipoPersona") String tipoPersona) {
    	personaService.eliminarContacto(idPersona, contactoId , tipoPersona);
    	return "redirect:/contacto/consultar-telefonos?id=" + idPersona;
    }
    
    ///////////////////////////////////////////
    ///////////////////////////////////////////
    //////////// VIEW: CREAR CORREO /////////// 
    ///////////////////////////////////////////
    ///////////////////////////////////////////
    

    @GetMapping("/consultar-correos")
    public String consultarCorreos(@RequestParam(value = "id") UUID id, ModelMap model) {
    	
    		PersonaDTO persona = personaService.buscarPersona(id);
    		if (persona.getContactos().isEmpty()) {
				persona.setContactos(new ArrayList<ContactoDTO>());
			}
    		model.put("persona", persona);
    		model.put("contactos", persona.getContactos());
    		return "/contacto/consultarCorreos.html";
    }
	
    @GetMapping("/editar-correo")
	public String editCorreo(@RequestParam(value = "id") UUID id,
			@RequestParam(value = "accion") String accion , 
			@RequestParam(value = "contactoId", required = false) UUID contactoId,
			@RequestParam(value = "tipoPersona") String tipoPersona,
			ModelMap model) {
    	
		PersonaDTO persona = personaService.buscarPersona(id);

		if (persona.getContactos().isEmpty()) {
			persona.setContactos(new ArrayList<ContactoDTO>());
		}
		
		ContactoDTO contacto = new ContactoDTO();
		
		if (contactoId != null) {
			 contacto = personaService.buscarContacto(id, tipoPersona, contactoId);
		}
		
		model.put("tipoContacto", TipoContacto.values());
		model.put("tipoTelefono", TipoTelefono.values());
		model.put("idPersona", id);
		model.put("accion", accion);
		model.put("contacto", contacto);
		model.put("tipoPersona", tipoPersona);
		
		return "/contacto/editCorreo.html";
	}
	
    @PostMapping("/actualizar-correo")
    public String actualizarCorreo(
            @RequestParam(value = "id") UUID idPersona,
            @RequestParam(value = "contactoId", required = false) UUID contactoId,
            @RequestParam(value = "tipoPersona") String tipoPersona,
            @RequestParam(value = "tipoContacto") TipoContacto tipoContacto,
            @RequestParam(value = "observacion") String observacion,
            @RequestParam(value = "email") String email,
            @RequestParam(value = "accion") String accion, 
            ModelMap model) {

        if (accion.equals("Crear")) {
        	personaService.crearCorreo(idPersona,tipoPersona, tipoContacto, observacion, email);
        } else {
            personaService.modificarCorreo(idPersona, contactoId, tipoPersona, tipoContacto, observacion, email);
        }

        // Redirigir pasando idPersona como parámetro
        return "redirect:/contacto/consultar-correos?id=" + idPersona;
    }

    @PostMapping("/eliminar-correo")
    public String eliminarCorreo(@RequestParam("id") UUID idPersona,
    		@RequestParam("contactoId") UUID contactoId,
    		@RequestParam("tipoPersona") String tipoPersona) {
    	personaService.eliminarContacto(idPersona, contactoId , tipoPersona);
    	return "redirect:/contacto/consultar-correos?id=" + idPersona;
    }
}
