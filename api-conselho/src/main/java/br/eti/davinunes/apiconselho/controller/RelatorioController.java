package br.eti.davinunes.apiconselho.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.eti.davinunes.apiconselho.entity.RelatorioData;

@RestController
@RequestMapping("/relatorio")
public class RelatorioController {

    @PostMapping("/gerar")
    public ResponseEntity<String> gerarRelatorio(@RequestBody RelatorioData data) {
        try {
            // Acesso aos parâmetros recebidos via POST
            String parametro1 = data.getParametro1();
            List<String> listaDeParametros = data.getListaDeParametros();

            // Validando se os dados foram lidos corretamente
            System.out.println("parametro1: " + parametro1);
            System.out.println("listaDeParametros: " + listaDeParametros);

            // Retornando uma resposta de sucesso
            return ResponseEntity.ok("Dados lidos com sucesso: " + parametro1 + ", " + listaDeParametros);
        } catch (Exception e) {
            // Tratamento de erros
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar os dados.");
        }
    }
}