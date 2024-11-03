package com.tpFinalLabo4.labo4.controller;


import com.tpFinalLabo4.labo4.model.Clase;
import com.tpFinalLabo4.labo4.model.Curso;
import com.tpFinalLabo4.labo4.service.IClaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/clase")
public class ClaseController {

    @Autowired
    private IClaseService interClase;

    @GetMapping
    public List<Clase> getClase() {
        return interClase.getClase();
    }


    @PostMapping("/{cursoId}")
    public String createClase(@RequestBody Clase clase, @PathVariable Long cursoId) {
        return interClase.saveClase(clase, cursoId);
    }

    @DeleteMapping("/{id}")
    public String deleteClase(@PathVariable Long id) {

        interClase.deleteClase(id);
        return "La clase fue eliminada correctamente";
    }


    @PutMapping("/{id}") //puede ser con el ID "/personas/editar/{id}"
    public Clase editClase(@PathVariable Long id,
                           @RequestParam("title") String nuevoTitle,
                           @RequestParam("descripcion") String nuevaDescripcion,
                           @RequestParam("url") String nuevaUrl) {
        Clase clase = interClase.findById(id);


        clase.setTitle(nuevoTitle);
        clase.setDescripcion(nuevaDescripcion);
        clase.setUrl(nuevaUrl);

        interClase.saveClase(clase);

        return clase;


    }


    @GetMapping("/{id}")
    public Clase findById(@PathVariable Long id) {
        Clase clase = interClase.findById(id);
        if (clase == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso no encontrado");
        }
        return clase;
    }
}

