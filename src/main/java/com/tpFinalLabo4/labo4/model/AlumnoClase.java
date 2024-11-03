package com.tpFinalLabo4.labo4.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
public class AlumnoClase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "alumno_id")
    private Alumno alumno;

    @ManyToOne
    @JoinColumn(name = "clase_id")
    private Clase clase;




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AlumnoClase that)) return false;
        return Objects.equals(getAlumno(), that.getAlumno()) && Objects.equals(getClase(), that.getClase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAlumno(), getClase());
    }
}

