package com.tpFinalLabo4.labo4.model;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
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

    // Guarda el id que se asigna desde el front
    //Pero quiero ver si puedo vincularlo con el id de cursos directamente referenciandolo
    @ManyToMany
    @JoinTable(
            name = "alumno_curso",
            joinColumns = @JoinColumn(name = "alumno_id"),
            inverseJoinColumns = @JoinColumn(name = "curso_id")
    )

    private Set<Curso> cursosInscritos = new HashSet<>();
}