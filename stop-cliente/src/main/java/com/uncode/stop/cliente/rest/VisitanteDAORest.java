package com.uncode.stop.cliente.rest;

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
public class VisitanteDAORest {

    @Autowired
    private RestTemplate restTemplate;

    private final String baseUri = "http://localhost:9000/visitantes";

    public void crear(VisitanteDTO visitante) {
        String uri = baseUri;
        restTemplate.postForEntity(uri, visitante, VisitanteDTO.class);
    }

    public void modificar(VisitanteDTO visitante) {
        String uri = baseUri + "/" + visitante.getId();
        restTemplate.put(uri, visitante);
    }

    public void eliminar(UUID id) {
        String uri = baseUri + "/" + id;
        restTemplate.delete(uri);
    }

    public VisitanteDTO buscar(UUID id) {
        String uri = baseUri + "/" + id;
        ResponseEntity<VisitanteDTO> response = restTemplate.getForEntity(uri, VisitanteDTO.class);
        VisitanteDTO visitante = response.getBody();
        return visitante;
    }

    public List<VisitanteDTO> listar() {
        String uri = baseUri;

        ResponseEntity<ResponseWrapper<VisitanteDTO>> response = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ResponseWrapper<VisitanteDTO>>() {}
        );

        List<VisitanteDTO> listaVisitantes = response.getBody().getContent();
        return listaVisitantes;
    }

}