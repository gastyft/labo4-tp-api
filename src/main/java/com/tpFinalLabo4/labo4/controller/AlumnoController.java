package com.tpFinalLabo4.labo4.controller;


import com.tpFinalLabo4.labo4.model.Alumno;
import com.tpFinalLabo4.labo4.model.AlumnoClase;
import com.tpFinalLabo4.labo4.model.Clase;
import com.tpFinalLabo4.labo4.model.Curso;
import com.tpFinalLabo4.labo4.service.IAlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/alumno")
public class AlumnoController {


    @Autowired
    private IAlumnoService interAlumno;

    @GetMapping
    public List<Alumno> getAlumno() {
        return interAlumno.getAlumno();
    }


    @PostMapping
    public String createAlumno(@RequestBody Alumno alumno) {
        interAlumno.saveAlumno(alumno);
        return "El alumno fue guardado correctamente";
    }

    @DeleteMapping("/{id}")
    public String deleteAlumno(@PathVariable Long id) {
        interAlumno.deleteAlumno(id);
        return "El alumno fue eliminado correctamente";
    }


    @PutMapping("/{id}")
    public Alumno editAlumno(@PathVariable Long id,
                           @RequestParam("nombre") String nuevoNombre,
                           @RequestParam("apellido") String nuevoApellido,
                           @RequestParam("email") String nuevoEmail,
                           @RequestParam("edad") int nuevaEdad) {
        Alumno alumno = interAlumno.findById(id);


        alumno.setNombre(nuevoNombre);
        alumno.setApellido(nuevoApellido);
        alumno.setEmail(nuevoEmail);
        alumno.setEdad(nuevaEdad);
        interAlumno.saveAlumno(alumno);

        return alumno;
    }


    @GetMapping("/{id}")
    public Alumno findById(@PathVariable Long id) {
        Alumno alumno = interAlumno.findById(id);
        if (alumno == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso no encontrado");
        }
        return alumno;
    }
    @DeleteMapping("/{alumnoId}/desinscribir/{cursoId}")
    public String desinscribirAlumnoDeCurso(@PathVariable Long alumnoId, @PathVariable Long cursoId) {
        return interAlumno.desinscribirAlumnoDeCurso(alumnoId, cursoId);
    }

    @PostMapping("/{alumnoId}/inscribir/{cursoId}")
    public ResponseEntity<String> inscribirAlumnoEnCurso(@PathVariable Long alumnoId, @PathVariable Long cursoId) {
        try {
            String resultado = interAlumno.inscribirAlumnoEnCurso(alumnoId, cursoId);
            if (resultado.equals("Alumno inscripto en el curso con éxito")) {
                return ResponseEntity.ok("Alumno inscripto correctamente");
            }else if (resultado.equals("El alumno ya está inscrito en este curso.")) {
                return  ResponseEntity.status(HttpStatus.CONFLICT).body("El alumno ya está inscrito en este curso.");
            }
            else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo inscribir el alumno");
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    //PARA CLASES VISTAS
    /*
    @PostMapping("{alumnoId}/visto/{claseId}")
    public String claseVistas(@PathVariable Long alumnoId, @PathVariable Long claseId){

        return interAlumno.claseVista(alumnoId, claseId);
    }
    @GetMapping("{alumnoId}/vistos")
    public List<AlumnoClase> clasesVistas(@PathVariable Long alumnoId){

        return interAlumno.clasesVistasxIdAlumno(alumnoId);
    }
    */

}



