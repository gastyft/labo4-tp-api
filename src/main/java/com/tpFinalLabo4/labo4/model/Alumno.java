package com.tpFinalLabo4.labo4.model;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Lazy;

import java.util.*;

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

    @ManyToMany(fetch = FetchType.LAZY)
    @Lazy
    @JoinTable(
            name = "alumno_curso",
            joinColumns = @JoinColumn(name = "alumno_id"),
            inverseJoinColumns = @JoinColumn(name = "curso_id")  
    )
    private List<Curso> cursosInscritos = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "alumno", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AlumnoClase> clasesVistas = new ArrayList<>();
}
