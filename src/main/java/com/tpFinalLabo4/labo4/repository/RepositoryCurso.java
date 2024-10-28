package com.tpFinalLabo4.labo4.repository;

import com.tpFinalLabo4.labo4.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RepositoryCurso extends JpaRepository<Curso,Long>{

        boolean existsByTitulo(String titulo);

}
