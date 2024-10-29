package com.tpFinalLabo4.labo4.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;


@Entity
@Getter @Setter
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String nombre;
    private String apellido;
    private String email;
    private int edad;

  //  @ElementCollection

    @OneToMany(mappedBy = "profesor", cascade = CascadeType.ALL, orphanRemoval = true)

    private Set<Curso> cursosQueDicta = new HashSet<>();
    //private List<Long> cursosQueDicta;
    // Usar SEts para las listas que no se repitan por ser unicas
    // e ignorar cursos para que en profesor, y alumno ya se traiga los datos directamente
    //Ver como crear luego de crear un profesor

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Profesor)) return false;
        Profesor profesor = (Profesor) o;
        return Objects.equals(id, profesor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}