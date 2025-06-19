package com.uncode.stop.cliente.rest;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.uncode.stop.cliente.dtos.UnidadNegocioDTO;
import com.uncode.stop.cliente.entities.ResponseWrapper;

@Service
public class UnidadNegocioDAORest {
	
	@Autowired
	private RestTemplate restTemplate;
	
	public void crear(UnidadNegocioDTO unidad) {
		String uri = "http://localhost:9000/unidades";
		restTemplate.postForEntity(uri, unidad, UnidadNegocioDTO.class);

	}

	public void modificar(UnidadNegocioDTO unidad) {

		String uri = "http://localhost:9000/unidades/" + unidad.getId();
		restTemplate.put(uri, unidad);

	}

	public void eliminar(UUID id) {

		String uri = "http://localhost:9000/unidades/" + id;
		restTemplate.delete(uri);
	}

	public UnidadNegocioDTO buscar(UUID id) {

		String uri = "http://localhost:9000/unidades/" + id;

		ResponseEntity<UnidadNegocioDTO> response = restTemplate.getForEntity(uri, UnidadNegocioDTO.class);
		UnidadNegocioDTO unidad = response.getBody();

		return unidad;
	}

	public List<UnidadNegocioDTO> listar() {
		String uri = "http://localhost:9000/unidades";

		// Deserializa la respuesta en el formato esperado (ResponseWrapper<PaisDTO>)
		ResponseEntity<ResponseWrapper<UnidadNegocioDTO>> response = restTemplate.exchange(uri, HttpMethod.GET, null,
				new ParameterizedTypeReference<ResponseWrapper<UnidadNegocioDTO>>() {
				});

		// Obtén la lista de países desde el "content" del ResponseWrapper
		List<UnidadNegocioDTO> listaUnidades = response.getBody().getContent();
		return listaUnidades;
	}
	
}
