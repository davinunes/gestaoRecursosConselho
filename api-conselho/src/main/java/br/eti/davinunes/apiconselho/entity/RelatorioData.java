package br.eti.davinunes.apiconselho.entity;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class RelatorioData {
    private String qualiProfName;
    private String ano;
    private String codomOmBase;
    private String nomeOmBase;
    private String siglaOmBase;
    private String certificadosOmBase;
    private String totalCertificados;
    private List<DetalhesTabela> listaDeParametros;
    

    // getters e setters
}

