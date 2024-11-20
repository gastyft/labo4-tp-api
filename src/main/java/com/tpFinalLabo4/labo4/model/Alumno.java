package com.tpFinalLabo4.labo4.model;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tpFinalLabo4.labo4.security.entity.Usuario;
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


    @OneToOne
    @JsonIgnore
    @Lazy
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;





    public Alumno( ){}

    public Alumno(Usuario usuario) {
        this.nombre = usuario.getNombre();
        this.apellido = usuario.getNombreUsuario();
        this.email = usuario.getEmail();
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }



}
