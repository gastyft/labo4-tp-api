package com.tpFinalLabo4.labo4.repository;

import com.tpFinalLabo4.labo4.model.Alumno;
import com.tpFinalLabo4.labo4.model.AlumnoClase;
import com.tpFinalLabo4.labo4.model.Clase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RepositoryAlumnoClase extends JpaRepository<AlumnoClase,Long> {

    Optional<AlumnoClase> findByAlumnoAndClase(Alumno alumno, Clase clase);

    // Consulta para obtener las clases vistas por un alumno
    @Query("SELECT ac.clase FROM AlumnoClase ac WHERE ac.alumno.id = :alumnoId")
    List<Clase> findClasesByAlumnoIdAndVistoTrue(Long alumnoId);
}

