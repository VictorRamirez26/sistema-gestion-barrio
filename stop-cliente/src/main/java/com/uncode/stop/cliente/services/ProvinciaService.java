package com.uncode.stop.cliente.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uncode.stop.cliente.dtos.PaisDTO;
import com.uncode.stop.cliente.dtos.ProvinciaDTO;
import com.uncode.stop.cliente.rest.PaisDAORest;
import com.uncode.stop.cliente.rest.ProvinciaDAORest;

@Service
public class ProvinciaService {

	@Autowired
	private ProvinciaDAORest dao;
	
	public void crear(UUID id , String nombre , UUID idPais) {
		
		PaisDTO pais = new PaisDTO();
		pais.setId(idPais);
		
		ProvinciaDTO provincia = ProvinciaDTO.builder().id(id).nombre(nombre).pais(pais).build();
		dao.crear(provincia);
	}
	
	public void modificar(UUID id, String nombre , UUID idPais) {

		PaisDTO pais = new PaisDTO();
		pais.setId(idPais);
		
		ProvinciaDTO provincia = ProvinciaDTO.builder().id(id).nombre(nombre).pais(pais).build();
		dao.modificar(provincia);
	}

	public ProvinciaDTO buscar (UUID id){
		
		ProvinciaDTO provincia = dao.buscar(id);
		return provincia;
	}

	public void eliminar(UUID id) {
		dao.eliminar(id);
	}

	public List<ProvinciaDTO> listar() {
		return dao.listar();
	}
	
	public List<ProvinciaDTO> listarProvinciasPorPais(UUID idPais) {
		return dao.listarProvinciasPorPais(idPais);
	}
}
