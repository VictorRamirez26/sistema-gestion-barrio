package com.uncode.stop.cliente.controller;

import com.uncode.stop.cliente.dtos.InmuebleDTO;
import com.uncode.stop.cliente.dtos.UnidadNegocioDTO;
import com.uncode.stop.cliente.enums.EstadoInmueble;
import com.uncode.stop.cliente.services.InmuebleService;
import com.uncode.stop.cliente.services.UnidadNegocioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/inmueble")
public class InmuebleController {

    @Autowired
    private InmuebleService inmuebleService;
    @Autowired
    private UnidadNegocioService unidadNegocioService;

    @GetMapping("/listarInmuebles")
    public String iniciarInmuebles(ModelMap model){
        List<InmuebleDTO> inmuebles = inmuebleService.listar();
        model.put("inmuebles", inmuebles);
        return "/inmueble/inmueble.html";
    }

    @GetMapping("/editar-inmueble")
    public String editInmueble(@RequestParam(value = "id", required = false) UUID id,
                               @RequestParam(value = "accion") String accion,
                               Model model) {

        InmuebleDTO inmueble = (id == null) ? new InmuebleDTO() : inmuebleService.buscar(id);

        model.addAttribute("inmueble", inmueble);
        model.addAttribute("accion", accion);
        model.addAttribute("estadosInmueble", EstadoInmueble.values());
        model.addAttribute("unidadesDeNegocio", unidadNegocioService.listar());

        return "inmueble/editInmueble";
    }

    @PostMapping("/actualizar-inmueble")
    public String actualizarInmueble(@RequestParam(value = "id", required = false) UUID id,
                                     @RequestParam("numeracion") String numeracion, @RequestParam("piso") String piso,
                                     @RequestParam("depto") String depto, @RequestParam("estadoInmueble") EstadoInmueble estadoInmueble,
                                     @RequestParam("unidadDeNegocio") UUID unidadDeNegocioId, @RequestParam("accion") String accion,
                                     ModelMap model) {

        UnidadNegocioDTO unidadDeNegocio = unidadNegocioService.buscar(unidadDeNegocioId);

        if(id == null) {
            inmuebleService.crear(numeracion, piso, depto, estadoInmueble, unidadDeNegocio);
        } else {
            inmuebleService.modificar(id, numeracion, piso, depto, estadoInmueble, unidadDeNegocio);
        }

        return "redirect:/inmueble/listarInmuebles";

    }

    @PostMapping("/eliminar-inmueble")
    public String eliminarInmueble(@RequestParam("id") UUID id) {
        inmuebleService.eliminar(id);
        return "redirect:/inmueble/listarInmuebles";
    }
    
    @GetMapping("/listarInmueblesPorBarrio/{id}")
    public ResponseEntity<List<InmuebleDTO>> listarInmueblesPorBarrio(@PathVariable("id") UUID barrioId) {
    	List<InmuebleDTO> inmuebles = inmuebleService.listarInmueblesPorBarrio(barrioId);
        return ResponseEntity.ok(inmuebles);  // Devolver inmuebles en formato JSON
    }

}
