package com.tpFinalLabo4.labo4.model;




import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Lazy;

import java.util.*;


@Entity
@Getter @Setter
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String titulo;
    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "profesor_id")
  @JsonIgnore
    @Lazy
    private Profesor profesor;


    @OneToMany(mappedBy = "curso", cascade = CascadeType.DETACH, orphanRemoval = true,fetch = FetchType.LAZY)
    @Lazy
    private List<Clase> clases;


    @ManyToMany(mappedBy = "cursosInscritos",fetch =FetchType.LAZY)
   @JsonIgnore
    @Lazy
    private List<Alumno> alumnosInscritos = new ArrayList<>();



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
