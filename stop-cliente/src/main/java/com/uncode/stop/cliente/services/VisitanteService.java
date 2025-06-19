package com.uncode.stop.cliente.services;

import com.uncode.stop.cliente.dtos.VisitanteDTO;
import com.uncode.stop.cliente.rest.VisitanteDAORest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class VisitanteService {

    @Autowired
    private VisitanteDAORest dao;

    public void crear(String nombre, String apellido, String numeroDeDocumento) {
        VisitanteDTO visitante = VisitanteDTO.builder().nombre(nombre).
                apellido(apellido).numeroDeDocumento(numeroDeDocumento).build();
        dao.crear(visitante);
    }

    public void modificar(UUID idVisitante, String nombre, String apellido,
                          String numeroDeDocumento) {
        VisitanteDTO visitante = VisitanteDTO.builder().nombre(nombre).
                id(idVisitante).apellido(apellido).numeroDeDocumento(numeroDeDocumento).
                build();
        dao.modificar(visitante);
    }

    public VisitanteDTO buscar(UUID id){
        VisitanteDTO visitante = dao.buscar(id);
        return visitante;
    }

    public void eliminar(UUID id) {
        dao.eliminar(id);
    }

    public List<VisitanteDTO> listar() {
        return dao.listar();
    }
}

