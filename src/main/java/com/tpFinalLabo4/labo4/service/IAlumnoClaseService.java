package com.tpFinalLabo4.labo4.service;

import com.tpFinalLabo4.labo4.model.Clase;

import java.util.List;

public interface IAlumnoClaseService {

    public List<Clase> obtenerClasesVistas(Long alumnoId);
    public void marcarClaseComoVista(Long alumnoId, Long claseId);

}
