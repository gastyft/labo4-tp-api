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
    public void inscribirAlumno(Alumno alumno, Curso curso){
        if (alumno.getCursosInscripto() == null) {
            alumno.setCursosInscripto(new ArrayList<>());
        }
        alumno.getCursosInscripto().add(curso.getId());
    }
}

