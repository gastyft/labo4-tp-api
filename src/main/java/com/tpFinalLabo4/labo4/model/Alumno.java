package com.tpFinalLabo4.labo4.model;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;


@Entity
@Getter @Setter
public class Alumno {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private int edad;

    //TODO Crear metodo para marcar el video como visto, dependiendo del alumno

    // Guarda el id que se asigna desde el front
    //Pero quiero ver si puedo vincularlo con el id de cursos directamente referenciandolo
    @ManyToMany
    @JoinTable(
            name = "alumno_curso",
            joinColumns = @JoinColumn(name = "alumno_id"),
            inverseJoinColumns = @JoinColumn(name = "curso_id")
    )
    private Set<Curso> cursosInscritos = new HashSet<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Alumno alumno)) return false;
        return getEdad() == alumno.getEdad() && Objects.equals(getId(), alumno.getId()) && Objects.equals(getNombre(), alumno.getNombre()) && Objects.equals(getApellido(), alumno.getApellido()) && Objects.equals(getEmail(), alumno.getEmail()) && Objects.equals(getCursosInscritos(), alumno.getCursosInscritos());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNombre(), getApellido(), getEmail(), getEdad(), getCursosInscritos());
    }
}