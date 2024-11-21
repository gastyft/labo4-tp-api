package com.tpFinalLabo4.labo4.controller;

import com.tpFinalLabo4.labo4.model.AlumnoCertificate;
import com.tpFinalLabo4.labo4.model.Curso;
import com.tpFinalLabo4.labo4.service.IAlumnoCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alumno-certificate")
public class AlumnoCertificateController {

        @Autowired
        private IAlumnoCertificateService alumnoCertificateService;

        // Endpoint para registrar un curso finalizado
        @PostMapping("/{alumnoId}/{cursoId}")
        public ResponseEntity<String> finalizarCurso(
                @PathVariable Long alumnoId,
                @PathVariable Long cursoId) {
            try {
                String response = alumnoCertificateService.cursoFinalizado(alumnoId, cursoId);
                return ResponseEntity.ok(response);
            } catch (RuntimeException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }


        @GetMapping("/{alumnoId}")
        public ResponseEntity<List<Curso>> obtenerCursosFinalizados(@PathVariable Long alumnoId) {
            try {
                List<Curso> cursos = alumnoCertificateService.obtenerCursosPorAlumno(alumnoId);
                return ResponseEntity.ok(cursos);
            } catch (RuntimeException e) {
                return ResponseEntity.badRequest().body(null);
            }
        }

    @GetMapping("/{alumnoId}/{cursoId}")
    public ResponseEntity<AlumnoCertificate> obtenerAlumnoCertificate(@PathVariable Long alumnoId, @PathVariable Long cursoId) {
        try {
            Optional<AlumnoCertificate> getAlumCert = alumnoCertificateService.obtenerPorAlumnoIdYCursoId(alumnoId, cursoId);

            if (getAlumCert.isPresent()) {
                // Si se encuentra el certificado, devolverlo en el cuerpo de la respuesta
                return ResponseEntity.ok(getAlumCert.get());
            } else {
                // Si no se encuentra, devolver una respuesta 404 (Not Found)
                return ResponseEntity.notFound().build();
            }
        } catch (RuntimeException e) {
            // Manejar cualquier excepción y devolver una respuesta 400 (Bad Request)
            return ResponseEntity.badRequest().body(null);
        }
    }
    }

