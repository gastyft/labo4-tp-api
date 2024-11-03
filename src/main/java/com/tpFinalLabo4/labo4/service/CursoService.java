package com.tpFinalLabo4.labo4.service;

import com.tpFinalLabo4.labo4.model.Curso;
import com.tpFinalLabo4.labo4.model.Profesor;
import com.tpFinalLabo4.labo4.repository.RepositoryCurso;
import com.tpFinalLabo4.labo4.repository.RepositoryProfesor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService implements ICursoService {

    @Autowired
    private RepositoryCurso cursoRepository;
    @Autowired
    private IProfesorService profesorService;
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
    @Override
    public String saveCurso(Curso curso, Long profesorId) {

       Profesor profesorOpt= profesorService.findById(profesorId);

        if (profesorOpt!= null) {
            curso.setProfesor(profesorOpt);

            // Guarda el curso en la base de datos
            Curso cursoGuardado = cursoRepository.save(curso);

            // Asocia el curso al profesor y guarda el profesor
            profesorOpt.getCursosQueDicta().add(cursoGuardado);
            profesorService.saveProfesor(profesorOpt);

            return "Curso creado y asignado al profesor con éxito";
        } else {
            throw new RuntimeException("Profesor no encontrado con ID: " + profesorId);
        }
    }
}
