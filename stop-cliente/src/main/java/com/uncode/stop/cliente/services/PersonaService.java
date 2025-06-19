package com.uncode.stop.cliente.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;

import com.uncode.stop.cliente.dtos.ContactoDTO;
import com.uncode.stop.cliente.dtos.EmpleadoDTO;
import com.uncode.stop.cliente.dtos.HabitanteDTO;
import com.uncode.stop.cliente.dtos.PersonaDTO;
import com.uncode.stop.cliente.enums.TipoContacto;
import com.uncode.stop.cliente.enums.TipoEmpleado;
import com.uncode.stop.cliente.enums.TipoTelefono;
import com.uncode.stop.cliente.rest.PersonaDAORest;

@Service
public class PersonaService {

	@Autowired
	private EmpleadoService empleadoService;
	
	@Autowired
	private HabitanteService habitanteService;
	
	@Autowired
	private PersonaDAORest dao;
	
	public List<PersonaDTO> listarPersonas() {
		
        List<PersonaDTO> personas = new ArrayList<>();

        // Obtener la lista de empleados y convertir a PersonaDTO
        List<EmpleadoDTO> empleados = empleadoService.listar();
        for (EmpleadoDTO empleado : empleados) {
            PersonaDTO persona = PersonaDTO.builder()
                    .id(empleado.getId())
                    .nombre(empleado.getNombre())
                    .apellido(empleado.getApellido())
                    .build();
            if (empleado.getContactos() == null) {
            	persona.setContactos(new ArrayList<ContactoDTO>());
			}
            personas.add(persona);
        }
        
        // Obtener la lista de habitantes y convertir a PersonaDTO
        List<HabitanteDTO> habitantes = habitanteService.listar();
        for (HabitanteDTO habitante : habitantes) {
        	
            PersonaDTO persona = PersonaDTO.builder()
                    .id(habitante.getId())
                    .nombre(habitante.getNombre())
                    .apellido(habitante.getApellido())
                    .build();
            
            if (habitante.getContactos() == null) {
				persona.setContactos(new ArrayList<ContactoDTO>());
			}
            personas.add(persona);
        }
        
        return personas;
    }
	
	public ContactoDTO buscarContacto(UUID idPersona,String tipoPersona, UUID idContacto) {
		return dao.buscarContacto(idPersona, tipoPersona, idContacto);
	}
	
	public PersonaDTO buscarPersona(UUID id) {
	    
	    PersonaDTO persona = new PersonaDTO();
	    
	    // Buscar en Empleado
	    EmpleadoDTO empleado = new EmpleadoDTO();
	    try {
	    	empleado = empleadoService.buscar(id);
		} catch (HttpClientErrorException e) {}
	    
	    if (empleado.getId() != null) { // Verifica si el empleado no está vacío
	    	persona.setApellido(empleado.getApellido());
	    	persona.setNombre(empleado.getNombre());
	        persona.setId(id);
	        persona.setContactos(empleado.getContactos());
	        persona.setTipoPersona("empleado");
	        return persona;
	    }
	    
	    // Buscar en Habitante
	    HabitanteDTO habitante = habitanteService.buscar(id);
	    if (habitante.getId() != null) { // Verifica si el habitante no está vacío
	    	persona.setApellido(habitante.getApellido());
	    	persona.setNombre(habitante.getNombre());
	        persona.setId(id);
	        persona.setContactos(habitante.getContactos());
	        persona.setTipoPersona("habitante");
	        return persona;
	    }
	    
	    return null; // No se encontró ni empleado ni habitante con datos
	}

	
	public void crearTelefono(UUID idPersona , String tipoPersona,  TipoContacto tipoContacto, 
			TipoTelefono tipoTelefono, String observacion, String telefono) {
		
		ContactoDTO contacto = ContactoDTO.builder().tipoContacto(tipoContacto)
				.tipoTelefono(tipoTelefono)
				.observacion(observacion)
				.telefono(telefono)
				.build();
		
		dao.crearContacto(idPersona, tipoPersona, contacto);
	}
	
	public void modificarTelefono(UUID idPersona, UUID contactoId, String tipoPersona,  TipoContacto tipoContacto, 
			TipoTelefono tipoTelefono, String observacion, String telefono) {
		
		ContactoDTO contacto = ContactoDTO.builder().id(contactoId)
				.tipoContacto(tipoContacto)
				.tipoTelefono(tipoTelefono)
				.observacion(observacion)
				.telefono(telefono)
				.build();
		
		dao.modificarContacto(idPersona, tipoPersona ,contacto);
	}
	
	public void eliminarContacto(UUID idPersona, UUID idContacto, String tipoPersona) {
		dao.eliminarContacto(idPersona, idContacto, tipoPersona);
	}
	

	public void crearCorreo(UUID idPersona, String tipoPersona, TipoContacto tipoContacto,
			String observacion, String email) {
		
		ContactoDTO contacto = ContactoDTO.builder().tipoContacto(tipoContacto)
				.email(email)
				.observacion(observacion)
				.build();
		
		dao.crearContacto(idPersona,tipoPersona, contacto);
	}
	
	public void modificarCorreo(UUID idPersona,UUID idContacto, String tipoPersona, TipoContacto tipoContacto,
			String observacion, String email) {
		
		ContactoDTO contacto = ContactoDTO.builder().id(idContacto)
				.email(email)
				.tipoContacto(tipoContacto)
				.observacion(observacion)
				.build();
		
		dao.modificarContacto(idPersona,tipoPersona, contacto);
	}
	
	
}
