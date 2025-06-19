package com.uncode.stop.cliente.rest;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.uncode.stop.cliente.dtos.DepartamentoDTO;
import com.uncode.stop.cliente.dtos.LocalidadDTO;
import com.uncode.stop.cliente.entities.ResponseWrapper;

@Service
public class LocalidadDAORest {
	
	@Autowired
	private RestTemplate restTemplate;
	
	public void crear(LocalidadDTO localidad) {
		String uri = "http://localhost:9000/localidades";
		restTemplate.postForEntity(uri, localidad, LocalidadDTO.class);

	}

	public void modificar(LocalidadDTO localidad) {

		String uri = "http://localhost:9000/localidades/" + localidad.getId();
		restTemplate.put(uri, localidad);

	}

	public void eliminar(UUID id) {

		String uri = "http://localhost:9000/localidades/" + id;
		restTemplate.delete(uri);
	}

	public LocalidadDTO buscar(UUID id) {

		String uri = "http://localhost:9000/localidades/" + id;

		ResponseEntity<LocalidadDTO> response = restTemplate.getForEntity(uri, LocalidadDTO.class);
		LocalidadDTO localidad = response.getBody();

		return localidad;
	}

	public List<LocalidadDTO> listar() {
		String uri = "http://localhost:9000/localidades";

		// Deserializa la respuesta en el formato esperado (ResponseWrapper<PaisDTO>)
		ResponseEntity<ResponseWrapper<LocalidadDTO>> response = restTemplate.exchange(uri, HttpMethod.GET, null,
				new ParameterizedTypeReference<ResponseWrapper<LocalidadDTO>>() {
				});

		// Obtén la lista de países desde el "content" del ResponseWrapper
		List<LocalidadDTO> listaLocalidades = response.getBody().getContent();
		return listaLocalidades;
	}
	
	public List<LocalidadDTO> listarLocalidadesPorDepartamento(UUID localidadesId) {
	    String uri = "http://localhost:9000/localidades/listarLocalidadesPorDepartamento/" + localidadesId;

	    ResponseEntity<List<LocalidadDTO>> response = restTemplate.exchange(
	            uri, 
	            HttpMethod.GET, 
	            null, 
	            new ParameterizedTypeReference<List<LocalidadDTO>>() {}
	    );

	    return response.getBody();
	}
}
