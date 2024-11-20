package com.tpFinalLabo4.labo4.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
public class AlumnoCertificate {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "alumno_id")
    private Alumno alumno;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    private Date fechaFinalizacion;

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Date getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(Date fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AlumnoCertificate that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(getAlumno(), that.getAlumno()) && Objects.equals(getCurso(), that.getCurso()) && Objects.equals(getFechaFinalizacion(), that.getFechaFinalizacion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getAlumno(), getCurso(), getFechaFinalizacion());
    }
}
