package com.tpFinalLabo4.labo4.service;

import com.tpFinalLabo4.labo4.model.Alumno;

import com.tpFinalLabo4.labo4.model.AlumnoClase;
import com.tpFinalLabo4.labo4.model.Clase;
import com.tpFinalLabo4.labo4.model.Curso;
import com.tpFinalLabo4.labo4.repository.RepositoryAlumno;

import com.tpFinalLabo4.labo4.security.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        // Obtener el alumno y el curso de las bases de datos
        Alumno alumno = alumnoRepository.findById(alumnoId).orElseThrow(() -> new RuntimeException("Alumno no encontrado"));
        Curso curso = cursoService.findById(cursoId);

        // Validar que el curso y el alumno existan
        if (curso != null && alumno != null) {
            // Verificar si el alumno ya está inscrito en el curso
            boolean isAlreadyEnrolled = alumno.getCursosInscritos().stream()
                    .anyMatch(c -> c.getId().equals(cursoId));

            if (isAlreadyEnrolled) {
                return "El alumno ya está inscrito en este curso.";
            }

            // Inscribir al alumno en el curso
            alumno.getCursosInscritos().add(curso);
            alumnoRepository.save(alumno); // Guarda la relación

            return "Alumno inscrito en el curso con éxito";
        } else {
            return "No se pudo inscribir el alumno. Verifique que el curso y el alumno existan.";
        }
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
    public Optional<Alumno> getByUsuario(Usuario usuario) {
        return alumnoRepository.findByUsuario(usuario);
    }
}


