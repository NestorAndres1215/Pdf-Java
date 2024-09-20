package generadorpdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.PDFont;

import java.io.IOException;

public class GeneradorPDF {

    public static void main(String[] args) {
        // Crear un documento PDF
        try (PDDocument document = new PDDocument()) {
            // Añadir una página al documento
            PDPage page = new PDPage();
            document.addPage(page);

            // Crear un flujo de contenido para escribir en la página
            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                // Escribir un título en el PDF
                contentStream.beginText();
                PDFont font = PDType1Font.TIMES_BOLD; // Cambia a la fuente que necesites
                contentStream.setFont(font, 12);
                contentStream.newLineAtOffset(100, 700);
                contentStream.showText("Tabla generada con Apache PDFBox");
                contentStream.endText();

                // Añadir una tabla (simplificada)
                String[][] content = {
                        {"ID", "Nombre", "Apellido"},
                        {"1", "Carlos", "Ramírez"},
                        {"2", "Ana", "López"},
                        {"3", "Juan", "Pérez"}
                };

                // Configuración básica de la tabla
                float margin = 100;
                float yStart = 650;
                float yPosition = yStart;
                float rowHeight = 20;
                float tableWidth = 400;
                float colWidth = tableWidth / 3;
                float textXOffset = 5;

                // Dibujar la tabla
                for (int i = 0; i < content.length; i++) {
                    for (int j = 0; j < content[i].length; j++) {
                        contentStream.addRect(margin + j * colWidth, yPosition, colWidth, rowHeight);
                        contentStream.beginText();
                        contentStream.setFont(PDType1Font.TIMES_ITALIC, 12);
                        contentStream.newLineAtOffset(margin + j * colWidth + textXOffset, yPosition + 5);
                        contentStream.showText(content[i][j]);
                        contentStream.endText();
                    }
                    yPosition -= rowHeight;
                }

                contentStream.stroke();  // Dibuja las líneas de la tabla
            }

            // Guardar el documento
            document.save("C:\\Users\\Usuario\\Downloads\\PDF\\pdfbox.pdf");
            System.out.println("PDF creado exitosamente con Apache PDFBox.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
