package com.tpFinalLabo4.labo4.service;

import com.tpFinalLabo4.labo4.model.Alumno;
import com.tpFinalLabo4.labo4.model.Curso;
import com.tpFinalLabo4.labo4.model.AlumnoCertificate;
import com.tpFinalLabo4.labo4.repository.RepositoryAlumno;
import com.tpFinalLabo4.labo4.repository.RepositoryCurso;
import com.tpFinalLabo4.labo4.repository.RepositoryAlumnoCertificate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Date;

@Service
public class AlumnoCertificateService implements IAlumnoCertificateService {

    @Autowired
    private RepositoryAlumno alumnoRepository;

    @Autowired
    private RepositoryCurso cursoRepository;

    @Autowired
    private RepositoryAlumnoCertificate alumnoCertificateRepository;

    @Override
    public String cursoFinalizado(Long alumnoId, Long cursoId) {
        // Buscar alumno y curso
        Optional<Alumno> alumno = alumnoRepository.findById(alumnoId);
        Optional<Curso> curso = cursoRepository.findById(cursoId);

        if (alumno.isPresent() && curso.isPresent()) {
            // Buscar si ya existe la relación en la tabla intermedia
            Optional<AlumnoCertificate> certificadoOpt = alumnoCertificateRepository.findByAlumnoAndCurso(alumno.get(), curso.get());
            AlumnoCertificate certificado = certificadoOpt.orElse(new AlumnoCertificate());

            // Crear o actualizar la relación
            certificado.setAlumno(alumno.get());
            certificado.setCurso(curso.get());
            certificado.setFechaFinalizacion(new Date());

            alumnoCertificateRepository.save(certificado);
            return "Curso finalizado registrado correctamente.";
        } else {
            throw new RuntimeException("Alumno o Curso no encontrados.");
        }
    }

    @Override
    public List<Curso> obtenerCursosPorAlumno(Long alumnoId) {
        return alumnoCertificateRepository.findCursosByAlumnoId(alumnoId);
    }
}

