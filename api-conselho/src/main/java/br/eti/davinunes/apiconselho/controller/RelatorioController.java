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
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@RestController
@RequestMapping("/relatorio")
public class RelatorioController {

    

    @PostMapping("/gerar")
    public ResponseEntity<byte[]> gerarRelatorio(@RequestBody RelatorioData data) {

        try {
            InputStream jasperStream = getClass().getResourceAsStream("/br/eti/davinunes/apiconselho/relatorios/Base.jasper");

            if (jasperStream == null) {
                // O arquivo Jasper não foi encontrado
                System.out.println("###########################################################################################");
                System.out.println("###########################################################################################");
                System.out.println("###########################################################################################");
                System.out.println("###########################################################################################");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new byte[0]);
            }

            // Crie um mapa para os parâmetros do relatório
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("parametro1", data.getParametro1());

            System.out.println("###########################################################################################");
            System.out.println(data.getListaDeParametros());
            System.out.println("###########################################################################################");


            // Converta a lista de DetalhesTabela em um JRDataSource (dependendo da sua lógica)
            JRDataSource dataSource = createDataSource(data.getListaDeParametros());

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperStream, parameters, dataSource);

            byte[] relatorioPDF = JasperExportManager.exportReportToPdf(jasperPrint);

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

    // Método para criar o JRDataSource a partir da lista de DetalhesTabela
    private JRDataSource createDataSource(List<DetalhesTabela> detalhesTabelaList) {
        // Verifique se a lista não é nula ou vazia
        if (detalhesTabelaList != null && !detalhesTabelaList.isEmpty()) {
            // Use JRBeanCollectionDataSource para mapear a lista para um JRDataSource
            return new JRBeanCollectionDataSource(detalhesTabelaList);
        } else {
            // Se a lista estiver vazia, use um JRDataSource vazio
            return new JREmptyDataSource();
        }
    }
}