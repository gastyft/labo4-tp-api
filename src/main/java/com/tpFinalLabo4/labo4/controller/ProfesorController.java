package com.tpFinalLabo4.labo4.controller;


import com.tpFinalLabo4.labo4.model.Profesor;
import com.tpFinalLabo4.labo4.service.IProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("/profesor")
public class ProfesorController {


    @Autowired
    private IProfesorService interProfesor;

    @GetMapping
    public List<Profesor> getProfesores() {
        return interProfesor.getProfesor();
    }


    @PostMapping
    public String createProfesor(@RequestBody Profesor profesor) {
        interProfesor.saveProfesor(profesor);
        return "El profesor fue guardado correctamente";
    }

    @DeleteMapping("/{id}")
    public String deleteProfesor(@PathVariable Long id) {
        interProfesor.deleteProfesor(id);
        return "El profesor fue eliminada correctamente";
    }


    @PutMapping("/{id}") //puede ser con el ID "/personas/editar/{id}"
    public Profesor editProfesor(@PathVariable Long id,
                              @RequestParam("nombre") String nuevoNombre,
                              @RequestParam("apellido") String nuevoApellido,
                              @RequestParam("email") String nuevoEmail,
                              @RequestParam("edad") int nuevaEdad) {
        Profesor profesor = interProfesor.findById(id);


        profesor.setNombre(nuevoNombre);
        profesor.setApellido(nuevoApellido);
        profesor.setEmail(nuevoEmail);
        profesor.setEdad(nuevaEdad);
        interProfesor.saveProfesor(profesor);

        return profesor;
    }


    @GetMapping("/{id}")
    public Profesor findById(@PathVariable Long id) {

        Profesor profesor = interProfesor.findById(id);
        if (profesor == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso no encontrado");
        }
        return profesor;
    }
    }
