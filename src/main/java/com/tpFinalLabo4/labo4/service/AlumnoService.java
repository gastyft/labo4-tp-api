package com.tpFinalLabo4.labo4.service;

import com.tpFinalLabo4.labo4.model.Alumno;

import com.tpFinalLabo4.labo4.model.Curso;
import com.tpFinalLabo4.labo4.repository.RepositoryAlumno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlumnoService implements  IAlumnoService{

    @Autowired
    private RepositoryAlumno alumnoRepository;
    @Autowired
    private  ICursoService cursoService;
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
    public void deleteAlumno(Long id){
        alumnoRepository.deleteById(id);

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

        alumno.getCursosInscritos().add(curso);

        alumnoRepository.save(alumno); // Guarda la relación
        return "Alumno inscripto en el curso con éxito";
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

        // Guarda la relación en la base de datos
        alumnoRepository.save(alumno);

        // Si es bidireccional, también se deberia eliminar el alumno de la lista del curso
        curso.getAlumnosInscritos().remove(alumno);
     //   cursoService.saveCurso(curso);

        return "Alumno desinscripto del curso con éxito";
    }
}


