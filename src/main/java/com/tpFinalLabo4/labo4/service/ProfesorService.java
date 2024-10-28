package com.tpFinalLabo4.labo4.service;


import com.tpFinalLabo4.labo4.model.Profesor;
import com.tpFinalLabo4.labo4.repository.RepositoryProfesor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesorService implements IProfesorService{

    @Autowired
    private RepositoryProfesor profesorRepository;

    @Override
    public List<Profesor> getProfesor (){
        List<Profesor> listaProfesor = profesorRepository.findAll();
        return listaProfesor;

    }


    @Override
    public void saveProfesor (Profesor profesor){
        profesorRepository.save(profesor);

    }

    @Override
    public void deleteProfesor(Long id){
        profesorRepository.deleteById(id);

    }

    @Override
    public Profesor findById (Long id){
        Profesor profesor = profesorRepository.findById(id).orElse(null);
        return profesor;

    }
}
