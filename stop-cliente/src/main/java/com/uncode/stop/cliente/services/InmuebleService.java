package com.uncode.stop.cliente.services;

import com.uncode.stop.cliente.dtos.InmuebleDTO;
import com.uncode.stop.cliente.dtos.UnidadNegocioDTO;
import com.uncode.stop.cliente.enums.EstadoInmueble;
import com.uncode.stop.cliente.rest.InmuebleDAORest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class InmuebleService {

    @Autowired
    public InmuebleDAORest dao;

    public void crear(String numeracion, String piso, String depto, EstadoInmueble estadoInmueble,
                      UnidadNegocioDTO unidadNegocio) {
        InmuebleDTO inmueble = InmuebleDTO.builder().numeracion(numeracion).piso(piso).
                depto(depto).estadoInmueble(estadoInmueble).unidadDeNegocio(unidadNegocio).build();
        dao.crear(inmueble);
    }

    public void modificar(UUID idInmueble, String numeracion, String piso, String depto, EstadoInmueble estadoInmueble,
                          UnidadNegocioDTO unidadNegocio) {
        InmuebleDTO inmueble = InmuebleDTO.builder().id(idInmueble).numeracion(numeracion).piso(piso).
                depto(depto).estadoInmueble(estadoInmueble).unidadDeNegocio(unidadNegocio).build();
        dao.modificar(inmueble);
    }

    public InmuebleDTO buscar(UUID id) {
        InmuebleDTO inmueble = dao.buscar(id);
        return inmueble;
    }

    public void eliminar(UUID id) {
        dao.eliminar(id);
    }

    public List<InmuebleDTO> listar() {
        return dao.listar();
    }
    
    public List<InmuebleDTO> listarInmueblesPorBarrio(UUID barrioId){
    	return dao.listarInmueblesPorBarrio(barrioId);
    }
    
}
