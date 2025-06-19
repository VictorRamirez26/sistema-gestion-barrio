package com.uncode.stop.cliente.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.uncode.stop.cliente.dtos.EmpleadoDTO;
import com.uncode.stop.cliente.dtos.UnidadNegocioDTO;
import com.uncode.stop.cliente.dtos.UsuarioDTO;
import com.uncode.stop.cliente.enums.Rol;
import com.uncode.stop.cliente.enums.TipoEmpleado;
import com.uncode.stop.cliente.rest.EmpleadoDAORest;

@Service
public class EmpleadoService {
	
	@Autowired
	private EmpleadoDAORest dao;
	
	public void crear(UUID id, String nombre, String apellido,String legajo, UUID unidadId,TipoEmpleado tipoEmpleado) {
		
		UnidadNegocioDTO unidad = new UnidadNegocioDTO();
		unidad.setId(unidadId);
		
		EmpleadoDTO empleado = EmpleadoDTO.builder().id(id).nombre(nombre).
				apellido(apellido).legajo(legajo).unidadDeNegocio(unidad).tipoEmpleado(tipoEmpleado).build();
		
		dao.crear(empleado);
	}
	
	public void modificar(UUID id, String nombre, String apellido,String legajo, UUID unidadId,TipoEmpleado tipoEmpleado) {

		UnidadNegocioDTO unidad = new UnidadNegocioDTO();
		unidad.setId(unidadId);
		
		EmpleadoDTO empleado = EmpleadoDTO.builder().id(id).nombre(nombre).
				apellido(apellido).legajo(legajo).unidadDeNegocio(unidad).tipoEmpleado(tipoEmpleado).build();
		
		dao.modificar(empleado);
	}

	public EmpleadoDTO buscar(UUID id){
		try {
			
			EmpleadoDTO empleado = dao.buscar(id);
			return empleado;
		} catch (HttpClientErrorException e) {
			throw e;
		}catch (Exception ex) {
			throw ex;
		}
	}

	public void eliminar(UUID id) {
		dao.eliminar(id);
	}

	public List<EmpleadoDTO> listar() {
		return dao.listar();
	}
	
	public void crearUsuario(UUID id, String cuenta, String clave,String confirmarClave, Rol rol) {
		
		UsuarioDTO usuario = UsuarioDTO.builder().cuenta(cuenta).clave(clave).rol(rol).confirmarClave(confirmarClave).build();
		EmpleadoDTO empleado = EmpleadoDTO.builder().id(id).usuario(usuario).build();
		dao.crearUsuario(empleado);
	}
	
	public void modificarUsuario(UUID id, String cuenta, String clave,String confirmarClave, Rol rol) {
		
		UsuarioDTO usuario = UsuarioDTO.builder().cuenta(cuenta).clave(clave).rol(rol).confirmarClave(confirmarClave).build();
		EmpleadoDTO empleado = EmpleadoDTO.builder().id(id).usuario(usuario).build();
		dao.modificarUsuario(empleado);
	}
	
	
}
