package com.tpFinalLabo4.labo4.service;

import com.tpFinalLabo4.labo4.model.Clase;
import com.tpFinalLabo4.labo4.model.Curso;

import java.util.List;

public interface ICursoService {

    public List<Curso> getCurso();

    public void saveCurso(Curso curso);

    public void deleteCurso(Long id);

    public Curso findById (Long id);
    String saveCurso(Curso curso, Long profesorId);;
}
