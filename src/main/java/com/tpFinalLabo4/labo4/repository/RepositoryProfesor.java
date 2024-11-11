package com.tpFinalLabo4.labo4.repository;

import com.tpFinalLabo4.labo4.model.Alumno;
import com.tpFinalLabo4.labo4.model.Profesor;
import com.tpFinalLabo4.labo4.security.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositoryProfesor extends JpaRepository<Profesor, Long> {
    Optional<Profesor> findByUsuario(Usuario usuario);
}
