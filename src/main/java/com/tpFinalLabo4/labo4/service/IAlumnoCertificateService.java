package com.tpFinalLabo4.labo4.service;


import com.tpFinalLabo4.labo4.model.Curso;

import java.util.List;

public interface IAlumnoCertificateService {
    public String cursoFinalizado(Long alumnoId, Long cursoId);

    public List<Curso> obtenerCursosPorAlumno(Long alumnoId);


}
