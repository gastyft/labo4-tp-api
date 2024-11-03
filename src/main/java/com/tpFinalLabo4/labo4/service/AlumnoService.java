package com.tpFinalLabo4.labo4.service;

import com.tpFinalLabo4.labo4.model.Alumno;

import com.tpFinalLabo4.labo4.model.AlumnoClase;
import com.tpFinalLabo4.labo4.model.Clase;
import com.tpFinalLabo4.labo4.model.Curso;
import com.tpFinalLabo4.labo4.repository.RepositoryAlumno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlumnoService implements  IAlumnoService{

    @Autowired
    private RepositoryAlumno alumnoRepository;
    @Autowired
    private  ICursoService cursoService;
    @Autowired
    private IClaseService claseService;

    @Override
    public List<Alumno> getAlumno (){
        List<Alumno> listaAlumno = alumnoRepository.findAll();
        return listaAlumno;
    }


    @Override
    public void saveAlumno (Alumno alumno){
        alumnoRepository.save(alumno);
    }

    @Override
    public void deleteAlumno(Long id) {
        try {
            alumnoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            // Aquí puedes manejar la excepción, como registrar el error o lanzar una nueva excepción con un mensaje.
            throw new RuntimeException("Alumno no encontrado con id: " + id);
        }
    }

    @Override
    public Alumno findById (Long id){
        Alumno alumno = alumnoRepository.findById(id).orElse(null);
        return alumno;

    }
    @Override
    public String inscribirAlumnoEnCurso(Long alumnoId, Long cursoId) {
        Alumno alumno = alumnoRepository.findById(alumnoId).orElseThrow(() -> new RuntimeException("Alumno no encontrado"));
        Curso curso = cursoService.findById(cursoId);

        if(curso !=null && alumno!= null) {
            alumno.getCursosInscritos().add(curso);
            alumnoRepository.save(alumno); // Guarda la relación
            return "Alumno inscripto en el curso con éxito";
        }
        else return "No se pudo inscribir el alumno";
    }
    @Override
    public String desinscribirAlumnoDeCurso(Long alumnoId, Long cursoId) {
        // Busca el alumno por ID y lanza una excepción si no se encuentra
        Alumno alumno = alumnoRepository.findById(alumnoId)
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));

        // Busca el curso por ID (asegúrate de manejar la excepción si no se encuentra)
        Curso curso = cursoService.findById(cursoId);
        if (curso == null) {
            throw new RuntimeException("Curso no encontrado");
        }

        // Elimina el curso de la lista de cursos inscriptos del alumno
        alumno.getCursosInscritos().remove(curso);


        alumnoRepository.save(alumno);

        // Si es bidireccional, también se deberia eliminar el alumno de la lista del curso
     //   curso.getAlumnosInscritos().remove(alumno);
     //   cursoService.saveCurso(curso);

        return "Alumno desinscripto del curso con éxito";
    }
    /* @Override
    public String claseVista(Long alumnoId, Long claseId){
        Alumno alumno = alumnoRepository.findById(alumnoId)
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));
        Clase clase = claseService.findById(claseId);
        if(alumno !=null && clase != null) {


            return "clase vista agregada";
        }
        else
            return "Error al guardar la clase vista";
    }
    @Override
    public List<AlumnoClase> clasesVistasxIdAlumno(Long alumnoId){
        Alumno alumno = alumnoRepository.findById(alumnoId)
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));
        List<AlumnoClase> clasesVistas = alumno.getClasesVistas();

                if(alumno!=null) return clasesVistas;
                else
                    return clasesVistas;
    }
*/
}


