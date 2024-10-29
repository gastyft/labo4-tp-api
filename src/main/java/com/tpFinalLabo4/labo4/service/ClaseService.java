package com.tpFinalLabo4.labo4.service;

import com.tpFinalLabo4.labo4.model.Clase;

import com.tpFinalLabo4.labo4.model.Curso;
import com.tpFinalLabo4.labo4.repository.RepositoryClase;

import com.tpFinalLabo4.labo4.repository.RepositoryCurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClaseService implements IClaseService {

        @Autowired
        private RepositoryClase claseRepository;
    @Autowired
    private ICursoService cursoService;

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

    @Override
    public String saveClase(Clase clase, Long cursoId) {
         Curso  cursoOpt = cursoService.findById(cursoId);

        if (cursoOpt !=null) {
            // Asigna el curso a la clase
            clase.setCurso(cursoOpt);

            // Guarda la clase en la base de datos
            Clase claseGuardada = claseRepository.save(clase);

            // Agrega la clase a la lista de clases del curso y guarda el curso actualizado
            cursoOpt.getClases().add(claseGuardada);
            cursoService.saveCurso(cursoOpt);

            return "Clase creada y asignada al curso con éxito";
        } else {
            throw new RuntimeException("Curso no encontrado con ID: " + cursoId);
        }
    }
    }

