package com.tpFinalLabo4.labo4.controller;


import com.tpFinalLabo4.labo4.model.Curso;
import com.tpFinalLabo4.labo4.service.ICursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/curso")
public class CursoController {

    @Autowired
    private ICursoService interCursos;

    @GetMapping("/get-cursos-list")
    public List<Curso> getCursos() {
        return interCursos.getCurso();
    }


    @PostMapping("/crear")
    public String createCurso(@RequestBody Curso curso) {


             interCursos.saveCurso(curso);
             return "Clase guardada con exito";
        }



    @DeleteMapping("/borrar/{id}")
    public String deleteCurso(@PathVariable Long id) {

        interCursos.deleteCurso(id);
        return "La clase fue eliminada correctamente";
    }


    @PutMapping("/editar/{id}")
    public Curso editCurso(@PathVariable Long id,
                           @RequestParam("titulo") String nuevoTitulo,
                           @RequestParam("descripcion") String nuevaDescripcion) {
        // Busca el curso por ID
        Curso curso = interCursos.findById(id);

        // Verifica si el curso existe
        if (curso != null) {
            // Actualiza los campos del curso
            curso.setTitulo(nuevoTitulo);
            curso.setDescripcion(nuevaDescripcion);
            // Guarda el curso actualizado
            interCursos.saveCurso(curso);
        }
        return curso;
    }



    @GetMapping("/traer/{id}")

    public Curso findCurso(@PathVariable Long id) {
        return interCursos.findById(id);

    }



}