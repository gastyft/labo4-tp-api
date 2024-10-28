package com.tpFinalLabo4.labo4.service;

import com.tpFinalLabo4.labo4.model.Clase;
import com.tpFinalLabo4.labo4.repository.RepositoryClase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaseService implements IClaseService {

        @Autowired
        private RepositoryClase claseRepository;

        @Override
        public List<Clase> getClase (){
            List<Clase> listaClase = claseRepository.findAll();
            return listaClase;

        }


        @Override
        public void saveClase (Clase clase){
            claseRepository.save(clase);

        }

        @Override
        public void deleteClase(Long id){
            claseRepository.deleteById(id);

        }

        @Override
        public Clase findById (Long id){
            Clase clase = claseRepository.findById(id).orElse(null);
            return clase;

        }
    }

