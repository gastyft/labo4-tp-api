package com.tpFinalLabo4.labo4.pdf;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.kernel.pdf.action.PdfAction;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.property.TextAlignment;



import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PdfCertificate {

    public static byte[] generateCertificate(String nombreAlumno, String nombreCurso) throws IOException {
        // ByteArrayOutputStream para almacenar el contenido del PDF
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        PdfWriter writer = new PdfWriter(byteArrayOutputStream);
        PdfDocument pdf = new PdfDocument(writer);

        float heightImage = 170 * 2.83465f;   // Convertir 160 mm a puntos
        float widthImage = (3508 * 170) / 2480;  // Mantener la proporción de la imagen original
        widthImage = widthImage * 2.83465f;

        // Crear la página con tamaño A4 apaisado
        PdfPage page = pdf.addNewPage(new com.itextpdf.kernel.geom.PageSize(widthImage, heightImage));

        // Crear el PdfCanvas para poder dibujar la imagen de fondo
        PdfCanvas canvas = new PdfCanvas(page);
        String imagePath = "src/main/resources/static/fondo_black.png";
        com.itextpdf.io.image.ImageData imageData = com.itextpdf.io.image.ImageDataFactory.create(imagePath);

        // Escalar la imagen para que se ajuste al tamaño de la página sin distorsionarla
        canvas.addImage(imageData, widthImage, 0, 0, heightImage, 0, 0);

        // Crear el Document para agregar texto sobre la imagen
        Document document = new Document(pdf);

        // Ajustar los márgenes para mover el contenido hacia arriba
        document.setMargins(40, 60, 0, 120);

        // Agregar el contenido del certificado sobre la imagen
        document.add(new Paragraph("CERTIFICADO DE FINALIZACIÓN")
                .setBold().setFontSize(24).setTextAlignment(TextAlignment.CENTER));
        document.add(new Paragraph("\n"));
        document.add(new Paragraph("La Universidad Tecnológica Nacional certifica que:")
                .setFontSize(14).setTextAlignment(TextAlignment.CENTER));
        document.add(new Paragraph("\n" + nombreAlumno + "\n")
                .setBold().setFontSize(18).setTextAlignment(TextAlignment.CENTER));
        document.add(new Paragraph("ha completado satisfactoriamente el curso: " + nombreCurso)
                .setFontSize(14).setTextAlignment(TextAlignment.CENTER));

        document.add(new Paragraph("\n\nFecha de emisión: " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .setFontSize(12).setTextAlignment(TextAlignment.RIGHT));
        document.add(new Paragraph("\nFirma autorizada: Dec. Fernando Scholtus")
                .setFontSize(12).setTextAlignment(TextAlignment.RIGHT));

        // Ahora agregamos el logo al final
        PdfCanvas logoCanvas = new PdfCanvas(page);
        Rectangle pageSize = page.getPageSize();
        float x = pageSize.getLeft() + 5;  // Ajustar la distancia desde el borde izquierdo
        float y = pageSize.getTop() - 129;   // Ajustar la distancia desde el borde superior

        try {
            // Cargar el logo de la universidad
            Image headerImage = new Image(ImageDataFactory.create("src/main/resources/static/UTN-favicon.png"));
            headerImage.setFixedPosition(x, y);  // Establecer la posición del logo
            headerImage.setWidth(120); // Tamaño del logo
            new Canvas(logoCanvas, pdf, pageSize).add(headerImage); // Añadir el logo a la página
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Cerrar el documento
        document.close();

        // Devolver el contenido PDF como un array de bytes
        return byteArrayOutputStream.toByteArray();
    }


    public static class HeaderEventHandler implements IEventHandler {
        @Override
        public void handleEvent(Event event) {
            PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
            PdfDocument pdfDoc = docEvent.getDocument();
            PdfPage page = docEvent.getPage();
            PdfCanvas canvas = new PdfCanvas(page.newContentStreamBefore(), page.getResources(), pdfDoc);

            // Posición del logo en el encabezado
            Rectangle pageSize = page.getPageSize();
            float x = pageSize.getLeft() + 40;  // Ajustar la distancia desde el borde izquierdo
            float y = pageSize.getTop() - 130;   // Ajustar la distancia desde el borde superior

            try {
                // Cargar el logo de la universidad
                Image headerImage = new Image(ImageDataFactory.create("src/main/resources/static/UTN-favicon.png"));
                headerImage.setFixedPosition(x, y);  // Establecer la posición del logo
                headerImage.setWidth(150); // Tamaño del logo
                new Canvas(canvas, pdfDoc, pageSize).add(headerImage); // Añadir el logo a la página
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


/*
    static class FooterEventHandler implements IEventHandler {
        @Override
        public void handleEvent(Event event) {
            PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
            PdfDocument pdfDoc = docEvent.getDocument();
            PdfPage page = docEvent.getPage();
            PdfCanvas canvas = new PdfCanvas(page.newContentStreamBefore(), page.getResources(), pdfDoc);

            Rectangle rect = page.getPageSize();
            float x = rect.getLeft() + 10;
            float y = rect.getBottom() + 20;

            Paragraph footer = new Paragraph("Dirección: Calle Buque Pesquero Dorrego N° 281, Mar del Plata, Argentina | Teléfono: (0223) 480-1220 | Email: \n" +
                    "informes@mdp.utn.edu.ar")
                    .setFontSize(10)
                    .setTextAlignment(TextAlignment.CENTER);

            Paragraph footer2 = new Paragraph()
                    .setFontSize(10)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginTop(50)
                    .add("\n")
                    .add(new com.itextpdf.layout.element.Link("Instagram: @utnmardelplata", PdfAction.createURI("http://www.instagram.com/utnmardelplata")))
                    .add("\n")
                    .add(new com.itextpdf.layout.element.Link("Sitio Web: mdp.utn.edu.ar", PdfAction.createURI("https://mdp.utn.edu.ar/")));

            Paragraph pageNumber = new Paragraph("Página " + pdfDoc.getPageNumber(page))
                    .setFontSize(10)
                    .setTextAlignment(TextAlignment.CENTER);

            new Canvas(canvas, pdfDoc, rect).showTextAligned(footer, x + rect.getWidth() / 2, y + 40, TextAlignment.CENTER);
            new Canvas(canvas, pdfDoc, rect).showTextAligned(footer2, x + rect.getWidth() / 2, y + 15, TextAlignment.CENTER);
            new Canvas(canvas, pdfDoc, rect).showTextAligned(pageNumber, x + rect.getWidth() / 2, y, TextAlignment.CENTER);
        }
    }
    */

