
package com.tpFinalLabo4.labo4.security.repository;


import java.util.Optional;

import com.tpFinalLabo4.labo4.security.entity.Rol;
import com.tpFinalLabo4.labo4.security.enums.RolNombre;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer>{
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
    
}
