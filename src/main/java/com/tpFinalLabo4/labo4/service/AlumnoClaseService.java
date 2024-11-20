package com.tpFinalLabo4.labo4.service;

import com.tpFinalLabo4.labo4.model.Alumno;
import com.tpFinalLabo4.labo4.model.AlumnoClase;
import com.tpFinalLabo4.labo4.model.Clase;
import com.tpFinalLabo4.labo4.repository.RepositoryAlumno;
import com.tpFinalLabo4.labo4.repository.RepositoryAlumnoClase;
import com.tpFinalLabo4.labo4.repository.RepositoryClase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlumnoClaseService implements  IAlumnoClaseService{

    @Autowired
    private RepositoryAlumnoClase alumnoClaseRepository;

    @Autowired
    private RepositoryAlumno alumnoRepository;

    @Autowired
    private RepositoryClase claseRepository;

    // Marcar una clase como vista por un alumno
    public String marcarClaseComoVista(Long alumnoId, Long claseId) {
        Optional<Alumno> alumno = alumnoRepository.findById(alumnoId);
        Optional<Clase> clase = claseRepository.findById(claseId);

        if (alumno.isPresent() && clase.isPresent()) {
            // Busca si ya existe la relación en la tabla pivot
            Optional<AlumnoClase> alumnoClaseOpt = alumnoClaseRepository.findByAlumnoAndClase(alumno.get(), clase.get());
            AlumnoClase alumnoClase = alumnoClaseOpt.orElse(new AlumnoClase());

            alumnoClase.setAlumno(alumno.get());
            alumnoClase.setClase(clase.get());


            alumnoClaseRepository.save(alumnoClase);
            return "Guardado";
        } else {
             throw new RuntimeException("Alumno o Clase no encontrados");
        }
    }

    // Obtener las clases vistas por un alumno
    public List<Clase> obtenerClasesVistas(Long alumnoId) {
        return alumnoClaseRepository.findClasesByAlumnoIdAndVistoTrue(alumnoId);
    }
}