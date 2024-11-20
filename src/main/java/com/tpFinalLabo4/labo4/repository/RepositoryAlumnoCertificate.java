package com.tpFinalLabo4.labo4.repository;


import com.tpFinalLabo4.labo4.model.Alumno;
import com.tpFinalLabo4.labo4.model.AlumnoCertificate;
import com.tpFinalLabo4.labo4.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Repository
public interface RepositoryAlumnoCertificate extends JpaRepository<AlumnoCertificate, Long>  {


    Optional<AlumnoCertificate> findByAlumnoAndCurso(Alumno alumno, Curso curso);

    @Query("SELECT ac.curso FROM AlumnoCertificate ac WHERE ac.alumno.id = :alumnoId")
    List<Curso> findCursosByAlumnoId(Long alumnoId);
}
