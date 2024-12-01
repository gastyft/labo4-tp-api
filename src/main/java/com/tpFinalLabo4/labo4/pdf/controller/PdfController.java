package com.tpFinalLabo4.labo4.pdf.controller;


import com.tpFinalLabo4.labo4.pdf.service.PdfService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;


@RestController
@RequestMapping("/generate-certificate")
public class PdfController {

    private final PdfService pdfService;

    public PdfController(PdfService pdfService) {
        this.pdfService = pdfService;
    }

    @GetMapping()
    public ResponseEntity<byte[]> generateCertificate(@RequestParam String nombreAlumno,
                                                      @RequestParam String nombreCurso,
                                                      @RequestParam String fechaFinalizacion) {

   

            LocalDate date = LocalDate.parse(fechaFinalizacion);

            byte[] pdfBytes = pdfService.generateCertificatePdf(nombreAlumno, nombreCurso, date);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Certificate_" + nombreCurso + "_" + nombreAlumno + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }
}