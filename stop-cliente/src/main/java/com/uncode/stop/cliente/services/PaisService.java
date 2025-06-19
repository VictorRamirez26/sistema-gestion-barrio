package com.uncode.stop.cliente.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uncode.stop.cliente.dtos.PaisDTO;
import com.uncode.stop.cliente.dtos.PersonaDTO;
import com.uncode.stop.cliente.enums.TipoContacto;
import com.uncode.stop.cliente.enums.TipoEmpleado;
import com.uncode.stop.cliente.enums.TipoTelefono;
import com.uncode.stop.cliente.rest.PaisDAORest;

@Service
public class PaisService {

	@Autowired
	private PaisDAORest dao;
	
	public void crear(String nombre) {
		
		PaisDTO pais = PaisDTO.builder().nombre(nombre).build();
		dao.crear(pais);
	}
	
	public void modificar(UUID idPais, String nombre) {

			PaisDTO pais = PaisDTO.builder().nombre(nombre).id(idPais).build();
			dao.modificar(pais);
	}

	public PaisDTO buscar (UUID id){
			PaisDTO pais = dao.buscar(id);
			return pais;
	}

	public void eliminar(UUID id) {
			dao.eliminar(id);
	}

	public List<PaisDTO> listar() {
			return dao.listar();
	}
	
}
