package com.tpFinalLabo4.labo4.repository;

import com.tpFinalLabo4.labo4.model.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryAlumno extends JpaRepository<Alumno, Long> {

}