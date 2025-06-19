package com.uncode.stop.cliente.rest;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.uncode.stop.cliente.dtos.EmpleadoDTO;
import com.uncode.stop.cliente.dtos.UsuarioDTO;
import com.uncode.stop.cliente.entities.ResponseWrapper;

@Service
public class EmpleadoDAORest {

	@Autowired
	private RestTemplate restTemplate;
	private final String baseUri = "http://localhost:9000/empleados";

	public void crear(EmpleadoDTO empleado) {

		String uri = baseUri;
		ResponseEntity<EmpleadoDTO> response = restTemplate.postForEntity(uri, empleado, EmpleadoDTO.class);

	}

	public void crearUsuario(EmpleadoDTO empleado) {

		String uri = baseUri + "/" + empleado.getId() + "/usuario";
		restTemplate.postForEntity(uri, empleado.getUsuario(), UsuarioDTO.class);

	}

	public void modificar(EmpleadoDTO empleado) {

		String uri = baseUri + empleado.getId();
		restTemplate.put(uri, empleado);

	}

	public void modificarUsuario(EmpleadoDTO empleado) {

		String uri = baseUri + "/" + empleado.getId() + "/usuario";
		restTemplate.put(uri, empleado.getUsuario());

	}

	public void eliminar(UUID id) {

		String uri = baseUri + "/" + id;
		restTemplate.delete(uri);
	}

	public void eliminarUsuario(UUID id) {

		String uri = baseUri + "/" + id + "/usuario";
		restTemplate.delete(uri);
	}

	public EmpleadoDTO buscar(UUID id) throws HttpClientErrorException {

		String uri = baseUri + "/" + id;
		try {
			ResponseEntity<EmpleadoDTO> response = restTemplate.getForEntity(uri, EmpleadoDTO.class);
			EmpleadoDTO empleado = response.getBody();
			return empleado;

		} catch (HttpClientErrorException e) {
			throw e;
			// TODO: handle exception
		} catch (Exception ex) {
			throw ex;
		}
	}

	public List<EmpleadoDTO> listar() {
		String uri = baseUri;

		// Deserializa la respuesta en el formato esperado (ResponseWrapper<PaisDTO>)
		ResponseEntity<ResponseWrapper<EmpleadoDTO>> response = restTemplate.exchange(
				uri,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<ResponseWrapper<EmpleadoDTO>>() {
				});

		// Obtén la lista de países desde el "content" del ResponseWrapper
		List<EmpleadoDTO> listaEmpleados = response.getBody().getContent();
		return listaEmpleados;
	}

}
