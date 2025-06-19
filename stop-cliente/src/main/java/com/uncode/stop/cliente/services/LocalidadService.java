package com.uncode.stop.cliente.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uncode.stop.cliente.dtos.DepartamentoDTO;
import com.uncode.stop.cliente.dtos.LocalidadDTO;
import com.uncode.stop.cliente.rest.LocalidadDAORest;

@Service
public class LocalidadService {
	
	@Autowired
	private LocalidadDAORest dao;
	
	public void crear(UUID id, String nombre, String codigoPostal, UUID idDepartamento) {
		
		DepartamentoDTO departamento = new DepartamentoDTO();
		departamento.setId(idDepartamento);
		
		LocalidadDTO localidad = LocalidadDTO.builder().id(id).nombre(nombre).codigoPostal(codigoPostal).
				departamento(departamento).build();
		
		dao.crear(localidad);
	}
	
	public void modificar(UUID id, String nombre, String codigoPostal, UUID idDepartamento) {

		DepartamentoDTO departamento = new DepartamentoDTO();
		departamento.setId(idDepartamento);
		
		LocalidadDTO localidad = LocalidadDTO.builder().id(id).nombre(nombre).codigoPostal(codigoPostal).
				departamento(departamento).build();
		
		dao.modificar(localidad);
	}

	public LocalidadDTO buscar(UUID id){
		
		LocalidadDTO localidad = dao.buscar(id);
		return localidad;
	}

	public void eliminar(UUID id) {
		dao.eliminar(id);
	}

	public List<LocalidadDTO> listar() {
		return dao.listar();
	}
	
	public List<LocalidadDTO> listarLocalidadesPorDepartamento(UUID id) {
		return dao.listarLocalidadesPorDepartamento(id);
	}
}
