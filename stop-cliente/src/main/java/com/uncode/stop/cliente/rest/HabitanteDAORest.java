package com.uncode.stop.cliente.rest;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.uncode.stop.cliente.dtos.EmpleadoDTO;
import com.uncode.stop.cliente.dtos.HabitanteDTO;
import com.uncode.stop.cliente.dtos.UsuarioDTO;
import com.uncode.stop.cliente.entities.ResponseWrapper;

@Service
public class HabitanteDAORest {
	
	@Autowired
	private RestTemplate restTemplate;
	private final String baseUri = "http://localhost:9000/habitantes";
	
	public void crear(HabitanteDTO habitante) {
		
		String uri = baseUri;
		restTemplate.postForEntity(uri, habitante, HabitanteDTO.class);
	
	}
	
	public void crearUsuario(HabitanteDTO habitante) {
		
		String uri = baseUri + "/" + habitante.getId() + "/usuario";
		restTemplate.postForEntity(uri, habitante.getUsuario(), UsuarioDTO.class);
	
	}
	
	public void modificar(HabitanteDTO habitante){
		
		 String uri = baseUri +"/"+ habitante.getId();
		 restTemplate.put(uri, habitante);
	
	}
	
	public void modificarUsuario(HabitanteDTO habitante){
		
		 String uri = baseUri + "/" + habitante.getId() + "/usuario";
		 restTemplate.put(uri, habitante.getUsuario());
	
	}
	
	public void eliminar(UUID id){
			
		String uri = baseUri + "/"+ id;
		restTemplate.delete(uri);
	}
	
	public void eliminarUsuario(UUID id){
		
		String uri = baseUri + "/" + id + "/usuario";
		restTemplate.delete(uri);
	}
		
	public HabitanteDTO buscar(UUID id) {
			
		String uri = baseUri + "/"+ id;
		
		ResponseEntity<HabitanteDTO> response = restTemplate.getForEntity(uri,HabitanteDTO.class);
		HabitanteDTO habitante = response.getBody();
		
		return  habitante;
	}
	
	public List<HabitanteDTO> listar(){
	    String uri = baseUri;
	    // Deserializa la respuesta en el formato esperado (ResponseWrapper<PaisDTO>)
	    ResponseEntity<ResponseWrapper<HabitanteDTO>> response = restTemplate.exchange(
	        uri,
	        HttpMethod.GET,
	        null,
	        new ParameterizedTypeReference<ResponseWrapper<HabitanteDTO>>() {}
	    );
	    
	    // Obtén la lista de países desde el "content" del ResponseWrapper
	    List<HabitanteDTO> listaHabitantes = response.getBody().getContent();
	    return listaHabitantes;
	}
}
