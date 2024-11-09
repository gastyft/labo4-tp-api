
package com.tpFinalLabo4.labo4.security.service;


import java.util.Optional;

import com.tpFinalLabo4.labo4.security.entity.Usuario;
import com.tpFinalLabo4.labo4.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;
    public Optional<Usuario> getByNombreUsuario(String nmbreUsuario){
        return usuarioRepository.findByNombreUsuario(nmbreUsuario);
    }
    
    public boolean existsByNombreUsuario(String nombreUsuario){
        return usuarioRepository.existsByNombreUsuario(nombreUsuario);
    }
    public boolean existsByEmail(String email){
        return usuarioRepository.existsByEmail(email);
        
    }
    public void save(Usuario usuario){
        usuarioRepository.save(usuario);
    }
    
}
