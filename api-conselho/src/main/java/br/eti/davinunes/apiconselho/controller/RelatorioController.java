package br.eti.davinunes.apiconselho.controller;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.eti.davinunes.apiconselho.entity.DetalhesTabela;
import br.eti.davinunes.apiconselho.entity.RelatorioData;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@RestController
@RequestMapping("/relatorio")
public class RelatorioController {

    

    @PostMapping("/gerar")
    public ResponseEntity<byte[]> gerarRelatorio(@RequestBody RelatorioData data) {

        System.out.println("Chamada Recebida!!");

        try {
            InputStream jasperStream = getClass().getResourceAsStream("/br/eti/davinunes/apiconselho/relatorios/Base.jasper");

            // Crie um mapa para os parâmetros do relatório
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("codomOmBase", data.getCodomOmBase());
            parameters.put("nomeOmBase", data.getNomeOmBase());
            parameters.put("siglaOmBase", data.getSiglaOmBase());
            parameters.put("certificadosOmBase", data.getCertificadosOmBase());

            // Converta a lista de DetalhesTabela em um JRDataSource (dependendo da sua lógica)

            // Crie uma lista de DetalhesTabela a partir dos dados
            List<DetalhesTabela> detalhesTabelaList = data.getListaDeParametros();

            // Converta a lista de DetalhesTabela em um JRDataSource
            // JRDataSource dataSource = new MeuJRDataSource(data.getListaDeParametros());
            JRDataSource dataSource =  new JRBeanCollectionDataSource(detalhesTabelaList);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperStream, parameters, dataSource);

            byte[] relatorioPDF = JasperExportManager.exportReportToPdf(jasperPrint);
            System.out.println(relatorioPDF);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "relatorio.pdf");

            return ResponseEntity.ok()
            .headers(headers)
            .contentLength(relatorioPDF.length)
            .body(relatorioPDF);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new byte[0]);
        }

    }
}