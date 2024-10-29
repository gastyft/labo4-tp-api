package com.tpFinalLabo4.labo4.controller;


import com.tpFinalLabo4.labo4.model.Profesor;
import com.tpFinalLabo4.labo4.service.IProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/profesor")
public class ProfesorController {


    @Autowired
    private IProfesorService interProfesor;

    @GetMapping("/get-profesor-list")
    public List<Profesor> getAlumno() {
        return interProfesor.getProfesor();
    }


    @PostMapping("/crear")
    public String createClase(@RequestBody Profesor profesor) {
        interProfesor.saveProfesor(profesor);
        return "El profesor fue guardado correctamente";
    }

    @DeleteMapping("/borrar/{id}")
    public String deleteClase(@PathVariable Long id) {
        interProfesor.deleteProfesor(id);
        return "El profesor fue eliminada correctamente";
    }


    @PutMapping("/editar/{id}") //puede ser con el ID "/personas/editar/{id}"
    public Profesor editClase(@PathVariable Long id,
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


    @GetMapping("/traer/{id}")
    public Profesor findById(@PathVariable Long id) {
        return interProfesor.findById(id);
    }





}