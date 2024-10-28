package com.tpFinalLabo4.labo4.repository;

import com.tpFinalLabo4.labo4.model.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryProfesor extends JpaRepository<Profesor, Long> {

}
