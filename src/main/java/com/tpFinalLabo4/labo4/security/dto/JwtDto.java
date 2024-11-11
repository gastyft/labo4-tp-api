
package com.tpFinalLabo4.labo4.security.dto;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;


public class JwtDto {
    private String token;
    private String bearer = "Bearer";
    private String nombreUsuario;
    private Collection<? extends GrantedAuthority> authorities;

    private Long idEntidad;
    public JwtDto(String token, String nombreUsuario, Collection<? extends GrantedAuthority> authorities,Long idEntidad) {
        this.token = token;
        this.nombreUsuario = nombreUsuario;
        this.authorities = authorities;
        this.idEntidad= idEntidad;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getBearer() {
        return bearer;
    }

    public void setBearer(String bearer) {
        this.bearer = bearer;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
    public Long getIdEntidad() {
        return idEntidad; // Getter para el id de la entidad
    }

    public void setIdEntidad(Long idEntidad) {
        this.idEntidad = idEntidad; // Setter para el id de la entidad
    }
}
