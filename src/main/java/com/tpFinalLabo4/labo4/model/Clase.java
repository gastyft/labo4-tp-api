package com.tpFinalLabo4.labo4.model;





import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;


@Entity
@Getter @Setter
public class Clase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private String title;
    private String descripcion;
    private String url;
    private String thumbnailUrl="";
    boolean isVisto=false;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    @JsonIgnore
    private Curso curso;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Clase clase)) return false;
        return isVisto() == clase.isVisto() && Objects.equals(getId(), clase.getId()) && Objects.equals(getTitle(), clase.getTitle()) && Objects.equals(getDescripcion(), clase.getDescripcion()) && Objects.equals(getUrl(), clase.getUrl()) && Objects.equals(getThumbnailUrl(), clase.getThumbnailUrl()) && Objects.equals(getCurso(), clase.getCurso());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getDescripcion(), getUrl(), getThumbnailUrl(), isVisto(), getCurso());
    }
}
