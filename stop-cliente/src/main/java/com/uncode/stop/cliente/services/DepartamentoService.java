package com.uncode.stop.cliente.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uncode.stop.cliente.dtos.DepartamentoDTO;
import com.uncode.stop.cliente.dtos.PaisDTO;
import com.uncode.stop.cliente.dtos.ProvinciaDTO;
import com.uncode.stop.cliente.rest.DepartamentoDAORest;

@Service
public class DepartamentoService {

	@Autowired
	private DepartamentoDAORest dao;
	
	public void crear(UUID id , String nombre , UUID idProvincia) {
		
		ProvinciaDTO provincia = new ProvinciaDTO();
		provincia.setId(idProvincia);
		
		DepartamentoDTO departamento = DepartamentoDTO.builder().id(id).nombre(nombre).provincia(provincia).build();
		dao.crear(departamento);
	}
	
	public void modificar(UUID id, String nombre , UUID idProvincia) {

		ProvinciaDTO provincia = new ProvinciaDTO();
		provincia.setId(idProvincia);
		
		DepartamentoDTO departamento = DepartamentoDTO.builder().id(id).nombre(nombre).provincia(provincia).build();
		dao.modificar(departamento);
	}

	public DepartamentoDTO buscar(UUID id){
		
		DepartamentoDTO departamento = dao.buscar(id);
		return departamento;
	}

	public void eliminar(UUID id) {
		dao.eliminar(id);
	}

	public List<DepartamentoDTO> listar() {
		return dao.listar();
	}
	
	public List<DepartamentoDTO> listarDepartamentosPorProvincia(UUID idProvincia) {
		return dao.listarDepartamentosPorProvincia(idProvincia);
	}
}
