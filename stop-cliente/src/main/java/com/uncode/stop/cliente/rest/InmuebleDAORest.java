package com.uncode.stop.cliente.rest;

import com.uncode.stop.cliente.dtos.InmuebleDTO;
import com.uncode.stop.cliente.dtos.LocalidadDTO;
import com.uncode.stop.cliente.dtos.VisitanteDTO;
import com.uncode.stop.cliente.entities.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Service
public class InmuebleDAORest {

    @Autowired
    private RestTemplate restTemplate;

    private final String baseUri = "http://localhost:9000/inmuebles";

    public void crear(InmuebleDTO inmueble) {
        String uri = baseUri;
        restTemplate.postForEntity(uri, inmueble, InmuebleDTO.class);
    }

    public void modificar(InmuebleDTO inmueble) {
        String uri = baseUri + "/" + inmueble.getId();
        restTemplate.put(uri, inmueble);
    }

    public void eliminar(UUID id) {
        String uri = baseUri + "/" + id;
        restTemplate.delete(uri);
    }

    public InmuebleDTO buscar(UUID id) {
        String uri = baseUri + "/" + id;
        ResponseEntity<InmuebleDTO> response = restTemplate.getForEntity(uri, InmuebleDTO.class);
        InmuebleDTO inmueble = response.getBody();
        return inmueble;
    }

    public List<InmuebleDTO> listar() {
        String uri = baseUri;

        ResponseEntity<ResponseWrapper<InmuebleDTO>> response = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ResponseWrapper<InmuebleDTO>>() {}
        );

        List<InmuebleDTO> listaInmuebles = response.getBody().getContent();
        return listaInmuebles;
    }

	public List<InmuebleDTO> listarInmueblesPorBarrio(UUID barrioId) {
	    String uri = baseUri + "/listarInmueblesPorBarrio/" + barrioId;

	    ResponseEntity<List<InmuebleDTO>> response = restTemplate.exchange(
	            uri, 
	            HttpMethod.GET, 
	            null, 
	            new ParameterizedTypeReference<List<InmuebleDTO>>() {}
	    );

	    return response.getBody();
	}
}
