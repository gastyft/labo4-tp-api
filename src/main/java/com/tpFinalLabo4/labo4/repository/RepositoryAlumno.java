package com.tpFinalLabo4.labo4.repository;

import com.tpFinalLabo4.labo4.model.Alumno;
import com.tpFinalLabo4.labo4.model.AlumnoClase;
import com.tpFinalLabo4.labo4.model.Clase;
import com.tpFinalLabo4.labo4.security.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositoryAlumno extends JpaRepository<Alumno, Long> {
    Optional<Alumno> findByUsuario(Usuario usuario);
}