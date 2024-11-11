
package com.tpFinalLabo4.labo4.security.controller;



import com.tpFinalLabo4.labo4.model.Alumno;
import com.tpFinalLabo4.labo4.model.Profesor;
import com.tpFinalLabo4.labo4.security.dto.JwtDto;
import com.tpFinalLabo4.labo4.security.dto.LoginUsuario;
import com.tpFinalLabo4.labo4.security.dto.Mensaje;
import com.tpFinalLabo4.labo4.security.dto.NuevoUsuario;
import com.tpFinalLabo4.labo4.security.entity.Rol;
import com.tpFinalLabo4.labo4.security.entity.Usuario;
import com.tpFinalLabo4.labo4.security.enums.RolNombre;
import com.tpFinalLabo4.labo4.security.jwt.JwtProvider;
import com.tpFinalLabo4.labo4.security.service.RolService;
import com.tpFinalLabo4.labo4.security.service.UsuarioService;
import com.tpFinalLabo4.labo4.service.AlumnoService;
import com.tpFinalLabo4.labo4.service.ProfesorService;
import jakarta.validation.Valid;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins="http://localhost:8080")
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    AlumnoService alumnoService;
    @Autowired
    ProfesorService profesorService;
    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(new Mensaje("Campos mal puestos o email inválido"), HttpStatus.BAD_REQUEST);

        if (usuarioService.existsByNombreUsuario(nuevoUsuario.getNombreUsuario()))
            return new ResponseEntity<>(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);

        if (usuarioService.existsByEmail(nuevoUsuario.getEmail()))
            return new ResponseEntity<>(new Mensaje("Ese email ya existe"), HttpStatus.BAD_REQUEST);

        Usuario usuario = new Usuario(
                nuevoUsuario.getNombre(),
                nuevoUsuario.getNombreUsuario(),
                nuevoUsuario.getEmail(),
                passwordEncoder.encode(nuevoUsuario.getPassword())
        );

        // Configuración de roles
        Set<Rol> roles = nuevoUsuario.getRoles().stream()
                .map(rolNombre -> rolService.getByRolNombre(RolNombre.valueOf(rolNombre.toUpperCase())).orElse(null))
                .collect(Collectors.toSet());

        usuario.setRoles(roles);
        usuarioService.save(usuario);

        // Verificación del rol para crear Alumno o Profesor
        if (roles.stream().anyMatch(rol -> rol.getRolNombre() == RolNombre.ROLE_ALUMNO)) {
            Alumno alumno = new Alumno();
            alumno.setUsuario(usuario);
            alumno.setNombre(nuevoUsuario.getNombre());
            alumno.setApellido(nuevoUsuario.getApellido());
            alumno.setEmail(nuevoUsuario.getEmail());
            alumno.setEdad(nuevoUsuario.getEdad());
            alumnoService.saveAlumno(alumno);

        } else if (roles.stream().anyMatch(rol -> rol.getRolNombre() == RolNombre.ROLE_PROFESOR)) {
            Profesor profesor = new Profesor();
            profesor.setUsuario(usuario);
            profesor.setNombre(nuevoUsuario.getNombre());
            profesor.setApellido(nuevoUsuario.getApellido());
            profesor.setEmail(nuevoUsuario.getEmail());
            profesor.setEdad(nuevoUsuario.getEdad());
            profesorService.saveProfesor(profesor);
        }

        return new ResponseEntity<>(new Mensaje("Usuario guardado"), HttpStatus.CREATED);
    }




    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("campos mal puestos"), HttpStatus.BAD_REQUEST);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        Usuario usuario = usuarioService.getByNombreUsuario(userDetails.getUsername()).orElse(null);
        Long idEntidad = null;

        if (usuario != null) {
            if (usuario.getRoles().stream().anyMatch(rol -> rol.getRolNombre().equals(RolNombre.ROLE_ALUMNO))) {
                Alumno alumno = alumnoService.getByUsuario(usuario).orElse(null);
                idEntidad = alumno != null ? alumno.getId() : null;
            } else if (usuario.getRoles().stream().anyMatch(rol -> rol.getRolNombre().equals(RolNombre.ROLE_PROFESOR))) {
                Profesor profesor = profesorService.getByUsuario(usuario).orElse(null);
                idEntidad = profesor != null ? profesor.getId() : null;
            }
        }

        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities(), idEntidad);
        return new ResponseEntity<>(jwtDto, HttpStatus.OK);
    }

}
