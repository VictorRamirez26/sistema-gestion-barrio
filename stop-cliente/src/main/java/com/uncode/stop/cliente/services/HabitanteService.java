package com.uncode.stop.cliente.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uncode.stop.cliente.dtos.EmpleadoDTO;
import com.uncode.stop.cliente.dtos.HabitanteDTO;
import com.uncode.stop.cliente.dtos.InmuebleDTO;
import com.uncode.stop.cliente.dtos.UsuarioDTO;
import com.uncode.stop.cliente.enums.Rol;
import com.uncode.stop.cliente.rest.HabitanteDAORest;


@Service
public class HabitanteService {
    
    @Autowired
    private HabitanteDAORest dao;

    // Crear un habitante
    public void crear(String nombre, String apellido, UUID inmuebleId) {
        
        InmuebleDTO inmueble = InmuebleDTO.builder().id(inmuebleId).build();
        
        HabitanteDTO habitante = HabitanteDTO.builder()
                .nombre(nombre)
                .apellido(apellido)
                .inmueble(inmueble)
                .build();
        
        dao.crear(habitante);
    }

    // Modificar un habitante
    public void modificar(UUID id, String nombre, String apellido, UUID inmuebleId) {

        InmuebleDTO inmueble = InmuebleDTO.builder().id(inmuebleId).build();
        
        HabitanteDTO habitante = HabitanteDTO.builder()
                .nombre(nombre)
                .apellido(apellido)
                .inmueble(inmueble)
                .build();
        
        dao.modificar(habitante);
    }

    // Buscar habitante por ID
    public HabitanteDTO buscar(UUID id) {
        return dao.buscar(id);
    }

    // Eliminar habitante por ID
    public void eliminar(UUID id) {
        dao.eliminar(id);
    }

    // Listar todos los habitantes
    public List<HabitanteDTO> listar() {
        return dao.listar();
    }
    
	public void crearUsuario(UUID id, String cuenta, String clave,String confirmarClave, Rol rol) {
		
		UsuarioDTO usuario = UsuarioDTO.builder().cuenta(cuenta).clave(clave).rol(rol).confirmarClave(confirmarClave).build();
		HabitanteDTO habitante = HabitanteDTO.builder().id(id).usuario(usuario).build();
		dao.crearUsuario(habitante);
	}
	
	public void modificarUsuario(UUID id, String cuenta, String clave,String confirmarClave, Rol rol) {
		
		UsuarioDTO usuario = UsuarioDTO.builder().cuenta(cuenta).clave(clave).rol(rol).confirmarClave(confirmarClave).build();
		HabitanteDTO habitante = HabitanteDTO.builder().id(id).usuario(usuario).build();
		dao.modificarUsuario(habitante);
	}
}
