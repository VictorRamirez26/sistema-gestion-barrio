package com.uncode.stop.cliente.rest;

import com.uncode.stop.cliente.dtos.ServicioDTO;
import com.uncode.stop.cliente.entities.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Service
public class ServicioDAORest {

	@Autowired
	private RestTemplate restTemplate;

	private final String baseUri = "http://localhost:9000/servicio";

	public void crear(String nombre, byte[] contenidoImagen, String nombreImagen, String mimeImagen) {
		String uri = baseUri + "/crear";

		// Crear MultiValueMap para enviar como multipart/form-data
		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		body.add("nombre", nombre);

		// Crear el recurso de la imagen
		ByteArrayResource imageResource = new ByteArrayResource(contenidoImagen) {
			@Override
			public String getFilename() {
				return nombreImagen;
			}
		};

		// Agregar la imagen como parte del multipart
		body.add("imagen", imageResource);

		// Configurar headers
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);

		// Crear la entidad HTTP con el body y headers
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

		// Realizar la petición POST
		restTemplate.exchange(
				uri,
				HttpMethod.POST,
				requestEntity,
				ServicioDTO.class
		);
	}

	// Los demás métodos permanecen igual...
	public void modificar(ServicioDTO servicio){
		String uri = baseUri + "/" + servicio.getId();
		restTemplate.put(uri, servicio);
	}

	public void eliminar(UUID id){
		String uri = baseUri + id;
		restTemplate.delete(uri);
	}

	public ServicioDTO buscar(UUID id) {
		String uri = baseUri + "/" + id;
		ResponseEntity<ServicioDTO> response = restTemplate.getForEntity(uri,ServicioDTO.class);
		return response.getBody();
	}

	public List<ServicioDTO> listar(){
		String uri = baseUri;
		ResponseEntity<ResponseWrapper<ServicioDTO>> response = restTemplate.exchange(
				uri,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<ResponseWrapper<ServicioDTO>>() {}
		);
		return response.getBody().getContent();
	}
}