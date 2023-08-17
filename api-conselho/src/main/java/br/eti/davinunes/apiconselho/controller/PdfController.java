package br.eti.davinunes.apiconselho.controller;

import java.io.ByteArrayOutputStream;
import java.util.Base64;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xhtmlrenderer.pdf.ITextRenderer;

@Controller
public class PdfController {

    @PostMapping(value = "/generate-pdf", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody
        public ResponseEntity<?> generatePdf(
            @RequestParam String tipo,
            @RequestBody String htmlContent) {

        try {
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(htmlContent, null);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            renderer.layout();
            renderer.createPDF(outputStream);

            byte[] pdfBytes = outputStream.toByteArray();

            if ("base64".equals(tipo)) {
                String pdfBase64 = convertToBase64(pdfBytes);
                return ResponseEntity.ok(pdfBase64);
            } else if ("file".equals(tipo)) {
                HttpHeaders headers = new HttpHeaders();
                headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=converted.pdf");
                return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
            } else {
                return ResponseEntity.badRequest().body("Tipo inválido");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions and return an appropriate response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro na geração do PDF");
        }
    }

    private String convertToBase64(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }
}
