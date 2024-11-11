
package com.tpFinalLabo4.labo4.security.util;



import com.tpFinalLabo4.labo4.security.entity.Rol;
import com.tpFinalLabo4.labo4.security.enums.RolNombre;
import com.tpFinalLabo4.labo4.security.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/*
@Component
public class createRoles implements CommandLineRunner {
       @Autowired
       RolService rolService;
    @Override
    public void run(String... args) throws Exception {
        Rol rolProfesor = new Rol(RolNombre.ROLE_PROFESOR);
        Rol rolAlumno = new Rol(RolNombre.ROLE_ALUMNO);
        rolService.save(rolProfesor);
        rolService.save(rolAlumno);
         
    }
} 

*/