package com.tpFinalLabo4.labo4.controller;


import com.tpFinalLabo4.labo4.model.Alumno;
import com.tpFinalLabo4.labo4.service.IAlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/alumno")
public class AlumnoController {


    @Autowired
    private IAlumnoService interAlumno;

    @GetMapping("/get-alumno-list")
    public List<Alumno> getAlumno() {
        return interAlumno.getAlumno();
    }


    @PostMapping("/crear")
    public String createClase(@RequestBody Alumno alumno) {
        interAlumno.saveAlumno(alumno);
        return "El alumno fue guardado correctamente";
    }

    @DeleteMapping("/borrar/{id}") //TODO Preguntar si meto exceptions y que sugerencia Ver tema de listas en models!
    public String deleteClase(@PathVariable Long id) {
        interAlumno.deleteAlumno(id);
        return "El alumno fue eliminado correctamente";
    }


    @PutMapping("/editar/{id}") //puede ser con el ID "/personas/editar/{id}"
    public Alumno editClase(@PathVariable Long id,
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


    @GetMapping("/traer/{id}")
    public Alumno findById(@PathVariable Long id) {
        return interAlumno.findById(id);

    }
}



