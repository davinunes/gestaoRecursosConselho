package br.eti.davinunes.apiconselho.controller;

import java.io.ByteArrayOutputStream;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xhtmlrenderer.pdf.ITextRenderer;

@Controller
public class PdfController {

    @PostMapping(value = "/generate-pdf", produces = "application/pdf")
    @ResponseBody
    public byte[] generatePdf(@RequestBody String htmlContent) {
        try {
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(htmlContent, null);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            renderer.layout();
            renderer.createPDF(outputStream);

            return outputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions and return an appropriate response
            return new byte[0];
        }
    }
}
