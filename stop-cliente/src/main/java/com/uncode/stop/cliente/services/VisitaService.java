package com.uncode.stop.cliente.services;

import com.uncode.stop.cliente.dtos.InmuebleDTO;
import com.uncode.stop.cliente.dtos.VisitaDTO;
import com.uncode.stop.cliente.dtos.VisitanteDTO;
import com.uncode.stop.cliente.enums.EstadoMovimiento;
import com.uncode.stop.cliente.enums.TipoMovilidad;
import com.uncode.stop.cliente.enums.TipoMovimiento;
import com.uncode.stop.cliente.enums.TipoVisita;
import com.uncode.stop.cliente.rest.VisitaDAORest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class VisitaService {

    @Autowired
    private VisitaDAORest dao;

    public void crear(TipoMovimiento tipoMovimiento,
                      String observacion,
                      EstadoMovimiento estadoMovimiento,
                      TipoMovilidad tipoMovilidad,
                      String descripcionMovilidad,
                      TipoVisita tipoVisita,
                      VisitanteDTO visitante,
                      InmuebleDTO inmueble,
                      LocalDateTime fechaMovimiento) {

        VisitaDTO visita = new VisitaDTO();
        visita.setTipoMovimiento(tipoMovimiento);
        visita.setFechaMovimiento(fechaMovimiento); // Or pass from parameter
        visita.setObservacion(observacion);
        visita.setEstadoMovimiento(estadoMovimiento);
        visita.setTipoMovilidad(tipoMovilidad);
        visita.setDescripcionMovilidad(descripcionMovilidad);
        visita.setTipoVisita(tipoVisita);
        visita.setVisitante(visitante);
        visita.setInmueble(inmueble);

        dao.crear(visita);
    }

    public void modificar(UUID id, TipoMovimiento tipoMovimiento, String observacion,
                          EstadoMovimiento estadoMovimiento, TipoMovilidad tipoMovilidad,
                          String descripcionMovilidad, TipoVisita tipoVisita,
                          VisitanteDTO visitanteDTO, InmuebleDTO inmuebleDTO,
                          LocalDateTime fechaMovimiento) {
        VisitaDTO visita = VisitaDTO.builder().id(id).tipoMovimiento(tipoMovimiento).
                observacion(observacion).estadoMovimiento(estadoMovimiento).
                tipoMovilidad(tipoMovilidad).descripcionMovilidad(descripcionMovilidad).
                tipoVisita(tipoVisita).visitante(visitanteDTO).inmueble(inmuebleDTO).fechaMovimiento(fechaMovimiento).build();
        dao.modificar(visita);
    }

    public VisitaDTO buscar(UUID id) {
        VisitaDTO visita = dao.buscar(id);
        return visita;
    }

    public void eliminar(UUID id) {
        dao.eliminar(id);
    }

    public List<VisitaDTO> listar() {
        return dao.listar();
    }

}
/*
"tipoMovimiento": "SALIDA",
            "fechaMovimiento": "2024-11-10T15:30:00",
            "observacion": "Ingreso de visitante al edificio",
            "estadoMovimiento": "REALIZADO",
            "tipoMovilidad": "AUTOMOVIL",
            "descripcionMovilidad": "Ingreso en camioneta",
            "tipoVisita": "FAMILIAR",
            "visitante": {
                "id": "da6ef235-e51a-454d-89d2-1124ecc38c0c",
                "nombre": "Victor",
                "apellido": "Ramirez",
                "numeroDeDocumento": "40000000"
            },
            "inmueble": {
                "id": "51adb003-cc08-4827-94d4-7770650b0b77",
                "numeracion": "2",
                "piso": "2",
                "depto": "B",
                "estadoInmueble": "HABITADO",
                "unidadDeNegocio": {
                    "id": "f5e32e10-44b3-4708-b319-c75a4c7cb943",
                    "nombre": "UNCuyo"
                }
            }
 */