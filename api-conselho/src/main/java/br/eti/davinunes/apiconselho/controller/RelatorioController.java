package br.eti.davinunes.apiconselho.controller;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.eti.davinunes.apiconselho.entity.RelatorioData;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;

@RestController
@RequestMapping("/relatorio")
public class RelatorioController {

    @Autowired
    private JasperReport jasperReport; // Injete o relatório Jasper aqui

    @PostMapping("/gerar")
    public ResponseEntity<ByteArrayResource> gerarRelatorio(@RequestBody RelatorioData data, HttpServletResponse response) {
        try {
            // Crie um mapa com os parâmetros necessários para o relatório
            Map<String, Object> params = new HashMap<>();
            params.put("parametro1", data.getParametro1());
            params.put("listaDeParametros", data.getListaDeParametros());

            // Gere o relatório em Jasper
            InputStream jasperStream = getClass().getResourceAsStream("/seu_relatorio.jasper"); // Substitua pelo caminho do seu relatório Jasper
            JasperPrint jasperPrint = JasperRunManager.runReportToPdf(jasperStream, params);

            // Converta o relatório em PDF em um array de bytes
            byte[] pdfBytes = JasperExportManager.exportReportToPdf(jasperPrint);

            // Crie uma resposta HTTP com o PDF
            ByteArrayResource resource = new ByteArrayResource(pdfBytes);
            return ResponseEntity.ok()
                    .header("Content-Disposition", "inline; filename=relatorio.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(resource);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

