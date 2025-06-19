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
import com.uncode.stop.cliente.dtos.ProvinciaDTO;
import com.uncode.stop.cliente.entities.ResponseWrapper;

@Service
public class ProvinciaDAORest {
	
	@Autowired
	private RestTemplate restTemplate;

	public void crear(ProvinciaDTO provincia) {

		String uri = "http://localhost:9000/provincias";
		restTemplate.postForEntity(uri, provincia, ProvinciaDTO.class);

	}

	public void modificar(ProvinciaDTO provincia) {

		String uri = "http://localhost:9000/provincias/" + provincia.getId();
		restTemplate.put(uri, provincia);

	}

	public void eliminar(UUID id) {

		String uri = "http://localhost:9000/provincias/" + id;
		restTemplate.delete(uri);
	}

	public ProvinciaDTO buscar(UUID id) {

		String uri = "http://localhost:9000/provincias/" + id;

		ResponseEntity<ProvinciaDTO> response = restTemplate.getForEntity(uri, ProvinciaDTO.class);
		ProvinciaDTO provincia = response.getBody();

		return provincia;
	}

	public List<ProvinciaDTO> listar() {
		String uri = "http://localhost:9000/provincias";

		ResponseEntity<ResponseWrapper<ProvinciaDTO>> response = restTemplate.exchange(uri, HttpMethod.GET, null,
				new ParameterizedTypeReference<ResponseWrapper<ProvinciaDTO>>() {
				});

		List<ProvinciaDTO> listaProvincias = response.getBody().getContent();
		return listaProvincias;
	}
	
	public List<ProvinciaDTO> listarProvinciasPorPais(UUID paisId) {
	    String uri = "http://localhost:9000/provincias/listarProvinciasPorPais/" + paisId;

	    ResponseEntity<List<ProvinciaDTO>> response = restTemplate.exchange(
	            uri, 
	            HttpMethod.GET, 
	            null, 
	            new ParameterizedTypeReference<List<ProvinciaDTO>>() {}
	    );

	    return response.getBody();
	}

	
}
