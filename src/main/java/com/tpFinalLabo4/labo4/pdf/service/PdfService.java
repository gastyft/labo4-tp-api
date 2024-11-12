package com.tpFinalLabo4.labo4.pdf.service;

import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.tpFinalLabo4.labo4.model.Alumno;
import com.tpFinalLabo4.labo4.pdf.PdfCertificate;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class PdfService {

    public  byte[] generateCertificatePdf(String nombreAlumno, String nombreCurso) {
        try {

          return  PdfCertificate.generateCertificate(nombreAlumno,nombreCurso);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
