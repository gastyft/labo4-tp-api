package com.tpFinalLabo4.labo4.service;

import com.tpFinalLabo4.labo4.model.Alumno;
import com.tpFinalLabo4.labo4.model.Curso;


import java.util.List;

public interface IAlumnoService {
    public List<Alumno> getAlumno();

    public void saveAlumno(Alumno alumno);

    public void deleteAlumno(Long id);

    public Alumno findById(Long id);
    public void inscribirAlumno(Alumno alumno, Curso curso);
}