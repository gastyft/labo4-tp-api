package com.tpFinalLabo4.labo4.model;





import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


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
    private String thumbnailUrl;
    boolean isVisto=false;

}
