package com.tpFinalLabo4.labo4.controller;

import com.tpFinalLabo4.labo4.model.Clase;
import com.tpFinalLabo4.labo4.service.AlumnoClaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alumno-clase")
public class AlumnoClaseController {

    @Autowired
    private AlumnoClaseService alumnoClaseService;

    // Endpoint para marcar una clase como vista
    @PostMapping("/marcar-visto")
    public void marcarClaseComoVista(
             @PathVariable Long alumnoId,
             @PathVariable Long claseId) {
        alumnoClaseService.marcarClaseComoVista(alumnoId, claseId);
    }

    // Endpoint para obtener las clases vistas de un alumno
    @GetMapping("/{alumnoId}/clases-vistas")
    public List<Clase> obtenerClasesVistas(@PathVariable Long alumnoId) {
        return alumnoClaseService.obtenerClasesVistas(alumnoId);
    }
}
