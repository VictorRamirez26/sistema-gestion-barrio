package com.uncode.stop.cliente.rest;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.uncode.stop.cliente.dtos.ContactoDTO;
import com.uncode.stop.cliente.dtos.EmpleadoDTO;
import com.uncode.stop.cliente.dtos.PersonaDTO;

@Service
public class PersonaDAORest {

	@Autowired
	private RestTemplate restTemplate;
	private final String baseUri = "http://localhost:9000";
	
	public void crearContacto(UUID idPersona,String tipoPersona, ContactoDTO contacto) {
		
		String str;
		if (tipoPersona.equals("empleado")) {
			str = "/empleados/";
		}else {
			str = "/habitantes/";
		}
		
		String uri = baseUri + str + idPersona + "/contactos";
		
		restTemplate.postForEntity(uri, contacto, ContactoDTO.class);
	}
	
	public void modificarContacto(UUID idPersona, String tipoPersona, ContactoDTO contacto){
		
		String str;
		if (tipoPersona.equals("empleado")) {
			str = "/empleados/";
		}else {
			str = "/habitantes/";
		}
		
		String uri = baseUri + str + idPersona +"/contactos/" + contacto.getId();
		restTemplate.put(uri, contacto);
	
	}
	
	public void eliminarContacto(UUID idPersona, UUID idContacto, String tipoPersona){
		
		String str;
		if (tipoPersona.equals("empleado")) {
			str = "/empleados/";
		}else {
			str = "/habitantes/";
		}
		
		String uri = baseUri + str + idPersona +"/contactos/" + idContacto;
		restTemplate.delete(uri);
	
	}
	
	public ContactoDTO buscarContacto(UUID idPersona, String tipoPersona, UUID idContacto) {
		
		String str;
		if (tipoPersona.equals("empleado")) {
			str = "/empleados/";
		}else {
			str = "/habitantes/";
		}
		
		String uri = baseUri + str + idPersona + "/contactos/" + idContacto;
		
		ResponseEntity<ContactoDTO> contacto = restTemplate.getForEntity(uri,ContactoDTO.class);
		return contacto.getBody();
	}
}
