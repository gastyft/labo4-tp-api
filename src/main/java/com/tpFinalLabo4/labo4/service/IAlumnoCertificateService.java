package com.tpFinalLabo4.labo4.service;


import com.tpFinalLabo4.labo4.model.AlumnoCertificate;
import com.tpFinalLabo4.labo4.model.Curso;

import java.util.List;
import java.util.Optional;

public interface IAlumnoCertificateService {
    public String cursoFinalizado(Long alumnoId, Long cursoId);

    public List<Curso> obtenerCursosPorAlumno(Long alumnoId);

    public Optional<AlumnoCertificate> obtenerPorAlumnoIdYCursoId(Long alumnoId, Long cursoId);
}
