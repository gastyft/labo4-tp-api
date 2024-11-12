
package com.tpFinalLabo4.labo4.security.service;


import java.util.Optional;

import com.tpFinalLabo4.labo4.security.entity.Rol;
import com.tpFinalLabo4.labo4.security.enums.RolNombre;
import com.tpFinalLabo4.labo4.security.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
public class RolService {

    @Autowired
    RolRepository rolRepository;

    public Optional<Rol> getByRolNombre(RolNombre rolNombre){
        return rolRepository.findByRolNombre(rolNombre);
    }

    public void save(Rol rol){
        rolRepository.save(rol);
    }
}
