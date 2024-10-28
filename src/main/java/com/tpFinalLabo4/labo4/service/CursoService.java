package com.tpFinalLabo4.labo4.service;

import com.tpFinalLabo4.labo4.model.Curso;
import com.tpFinalLabo4.labo4.repository.RepositoryCurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService implements ICursoService {

    @Autowired
    private RepositoryCurso cursoRepository;

    @Override
    public List<Curso> getCurso (){
        List<Curso> listacurso = cursoRepository.findAll();
        return listacurso;

    }


    @Override
    public void saveCurso (Curso curso){
        cursoRepository.save(curso);

    }

    @Override
    public void deleteCurso(Long id){
        cursoRepository.deleteById(id);

    }

    @Override
    public Curso findById (Long id){
        Curso curso = cursoRepository.findById(id).orElse(null);
        return curso;

    }
}
