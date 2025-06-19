package com.uncode.stop.cliente.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uncode.stop.cliente.dtos.DepartamentoDTO;
import com.uncode.stop.cliente.dtos.DireccionDTO;
import com.uncode.stop.cliente.dtos.LocalidadDTO;
import com.uncode.stop.cliente.dtos.ServicioDTO;
import com.uncode.stop.cliente.dtos.UnidadNegocioDTO;
import com.uncode.stop.cliente.rest.UnidadNegocioDAORest;

@Service
public class UnidadNegocioService {

	@Autowired
	private UnidadNegocioDAORest dao;
	
	
	public void crear(UUID id, String nombre,String calle, String numeracion, String latitud, String longitud, 
			UUID idLocalidad, UUID idServicio) {
		
		LocalidadDTO localidad = new LocalidadDTO();
		localidad.setId(idLocalidad);
		
		ServicioDTO servicio = new ServicioDTO();
		servicio.setId(idServicio);
		
		DireccionDTO direccion = DireccionDTO.builder().calle(calle).numeracion(numeracion)
				.latitud(latitud).longitud(longitud).localidad(localidad).build();

		UnidadNegocioDTO unidad = UnidadNegocioDTO.builder().id(id).nombre(nombre).
				direccion(direccion).servicio(servicio).build();
		dao.crear(unidad);
	}
	
	public void modificar(UUID id, String nombre,String calle, String numeracion, String latitud, String longitud, 
			UUID idLocalidad, UUID idServicio) {

		LocalidadDTO localidad = new LocalidadDTO();
		localidad.setId(idLocalidad);
		
		ServicioDTO servicio = new ServicioDTO();
		servicio.setId(idServicio);
		
		DireccionDTO direccion = DireccionDTO.builder().calle(calle).numeracion(numeracion)
				.latitud(latitud).longitud(longitud).localidad(localidad).build();

		UnidadNegocioDTO unidad = UnidadNegocioDTO.builder().id(id).nombre(nombre).
				direccion(direccion).servicio(servicio).build();
		
		dao.modificar(unidad);
	}

	public UnidadNegocioDTO buscar(UUID id){
		
		UnidadNegocioDTO unidad = dao.buscar(id);
		return unidad;
	}

	public void eliminar(UUID id) {
		dao.eliminar(id);
	}

	public List<UnidadNegocioDTO> listar() {
		return dao.listar();
	}
}
