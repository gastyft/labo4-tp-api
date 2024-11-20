package com.tpFinalLabo4.labo4.controller;

import com.tpFinalLabo4.labo4.model.Curso;
import com.tpFinalLabo4.labo4.service.IAlumnoCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/alumno-certificate")
public class AlumnoCertificateController {

        @Autowired
        private IAlumnoCertificateService alumnoCertificateService;

        // Endpoint para registrar un curso finalizado
        @PostMapping
        public ResponseEntity<String> finalizarCurso(
                @RequestParam Long alumnoId,
                @RequestParam Long cursoId) {
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
    }

