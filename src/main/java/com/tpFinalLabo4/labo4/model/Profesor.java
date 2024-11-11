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
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String nombre;
    private String apellido;
    private String email;
    private int edad;

    @OneToMany(mappedBy = "profesor", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    private List<Curso> cursosQueDicta = new ArrayList<>();

    @OneToOne
    @JsonIgnore
    @Lazy
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    public Profesor( ){}
    public Profesor(Usuario usuario) {
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
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Profesor)) return false;
        Profesor profesor;
        profesor = (Profesor) o;
        return Objects.equals(id, profesor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}