package com.uncode.stop.cliente.rest;



import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.uncode.stop.cliente.dtos.PaisDTO;
import com.uncode.stop.cliente.entities.ResponseWrapper;

@Service
public class PaisDAORest {

	@Autowired
	private RestTemplate restTemplate;
	
	public void crear(PaisDTO pais) {
		
		String uri = "http://localhost:9000/paises";
		restTemplate.postForEntity(uri, pais, PaisDTO.class);
	
	}
	
	public void modificar(PaisDTO pais){
		
		 String uri = "http://localhost:9000/paises/"+ pais.getId();
		 restTemplate.put(uri, pais);
	
	}
		
	public void eliminar(UUID id){
			
		String uri = "http://localhost:9000/paises/"+ id;
		restTemplate.delete(uri);
	}
		
	public PaisDTO buscar(UUID id) {
			
		String uri = "http://localhost:9000/paises/"+ id;
		
		ResponseEntity<PaisDTO> response = restTemplate.getForEntity(uri,PaisDTO.class);
		PaisDTO pais = response.getBody();
		
		return  pais;
	}
	
	public List<PaisDTO> listar(){
	    String uri = "http://localhost:9000/paises";
	    
	    // Deserializa la respuesta en el formato esperado (ResponseWrapper<PaisDTO>)
	    ResponseEntity<ResponseWrapper<PaisDTO>> response = restTemplate.exchange(
	        uri,
	        HttpMethod.GET,
	        null,
	        new ParameterizedTypeReference<ResponseWrapper<PaisDTO>>() {}
	    );
	    
	    // Obtén la lista de países desde el "content" del ResponseWrapper
	    List<PaisDTO> listaPaises = response.getBody().getContent();
	    return listaPaises;
	}

	
}
