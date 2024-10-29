package com.tpFinalLabo4.labo4.model;




import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;


@Entity
@Getter @Setter
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String titulo;
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "profesor_id")
    @JsonIgnore
    private Profesor profesor;


    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Clase> clases;


    @ManyToMany(mappedBy = "cursosInscritos")
    @JsonIgnore
    private Set<Alumno> alumnosInscritos = new HashSet<>();
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Curso)) return false;
        Curso curso = (Curso) o;
        return Objects.equals(id, curso.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
