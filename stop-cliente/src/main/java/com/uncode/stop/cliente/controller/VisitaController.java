package com.uncode.stop.cliente.controller;

import com.uncode.stop.cliente.dtos.InmuebleDTO;
import com.uncode.stop.cliente.dtos.VisitaDTO;
import com.uncode.stop.cliente.dtos.VisitanteDTO;
import com.uncode.stop.cliente.enums.EstadoMovimiento;
import com.uncode.stop.cliente.enums.TipoMovilidad;
import com.uncode.stop.cliente.enums.TipoMovimiento;
import com.uncode.stop.cliente.enums.TipoVisita;
import com.uncode.stop.cliente.services.InmuebleService;
import com.uncode.stop.cliente.services.VisitaService;
import com.uncode.stop.cliente.services.VisitanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/visita")
public class VisitaController {

    @Autowired
    private VisitaService visitaService;
    @Autowired
    private VisitanteService visitanteService;
    @Autowired
    private InmuebleService inmuebleService;

    @GetMapping("/listarVisitas")
    public String iniciarVisitas(ModelMap model){
        List<VisitaDTO> visitas = visitaService.listar();
        model.put("visitas", visitas);
        return "/visita/visita.html";
    }

    @GetMapping("/editar-visita")
    public String editVisita(@RequestParam(value = "id", required = false) UUID id,
                             @RequestParam(value = "accion") String accion,
                             Model model) {

        VisitaDTO visita = (id == null) ? new VisitaDTO() : visitaService.buscar(id);

        model.addAttribute("visita", visita);
        model.addAttribute("accion", accion);
        model.addAttribute("tiposMovimiento", TipoMovimiento.values());
        model.addAttribute("estadosMovimiento", EstadoMovimiento.values());
        model.addAttribute("tiposMovilidad", TipoMovilidad.values());
        model.addAttribute("tiposVisita", TipoVisita.values());
        model.addAttribute("inmuebles", inmuebleService.listar());
        model.addAttribute("visitantes", visitanteService.listar());

        return "visita/editVisita";
    }

    @PostMapping("/actualizar-visita")
    public String actualizarVisita(@RequestParam(value = "id", required = false) UUID id,
                                   @RequestParam("tipoMovimiento") TipoMovimiento tipoMovimiento,
                                   @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
                                   @RequestParam("fechaMovimiento") LocalDateTime fechaMovimiento,
                                   @RequestParam("observacion") String observacion,
                                   @RequestParam("estadoMovimiento") EstadoMovimiento estadoMovimiento,
                                   @RequestParam("tipoMovilidad") TipoMovilidad tipoMovilidad,
                                   @RequestParam("descripcionMovilidad") String descripcionMovilidad,
                                   @RequestParam("tipoVisita") TipoVisita tipoVisita,
                                   @RequestParam("visitante") UUID visitanteId,
                                   @RequestParam("inmueble") UUID inmuebleId,
                                   @RequestParam("accion") String accion,
                                   ModelMap model) {

        VisitanteDTO visitante = visitanteService.buscar(visitanteId);
        InmuebleDTO inmueble = inmuebleService.buscar(inmuebleId);

        if(id == null) {
            visitaService.crear(tipoMovimiento, observacion, estadoMovimiento, tipoMovilidad, descripcionMovilidad, tipoVisita, visitante, inmueble, fechaMovimiento);
        } else {
            visitaService.modificar(id, tipoMovimiento, observacion, estadoMovimiento, tipoMovilidad, descripcionMovilidad, tipoVisita, visitante, inmueble, fechaMovimiento);
        }
        return "redirect:/visita/listarVisitas";
    }

}