package com.tpFinalLabo4.labo4.service;

import com.tpFinalLabo4.labo4.model.Profesor;
import java.util.List;

public interface IProfesorService {
    public List<Profesor> getProfesor();

    public void saveProfesor(Profesor profesor);

    public void deleteProfesor(Long id);

    public Profesor findById(Long id);
}
