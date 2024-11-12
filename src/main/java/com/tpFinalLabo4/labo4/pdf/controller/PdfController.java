package com.tpFinalLabo4.labo4.pdf.controller;


import com.tpFinalLabo4.labo4.pdf.service.PdfService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/generate-certificate")
public class PdfController {


    private final PdfService pdfService;

    public PdfController(PdfService pdfService) {
        this.pdfService = pdfService;
    }

    @GetMapping()
public ResponseEntity<byte[]> generateCertificate(@RequestParam String nombreAlumno, @RequestParam String nombreCurso) {
    byte[] pdfBytes = pdfService.generateCertificatePdf(nombreAlumno, nombreCurso);

    return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Certificate_"+nombreCurso+"_"+nombreAlumno+".pdf")
            .contentType(MediaType.APPLICATION_PDF)
            .body(pdfBytes);
}
}