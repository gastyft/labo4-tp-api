package com.tpFinalLabo4.labo4.service;

import com.tpFinalLabo4.labo4.model.Clase;

import java.util.List;

public interface IClaseService {
    public List<Clase> getClase();

    public void saveClase(Clase clase);

    public void deleteClase(Long id);

    public Clase findById(Long id);

}

