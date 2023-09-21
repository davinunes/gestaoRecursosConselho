package br.eti.davinunes.apiconselho.entity;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class RelatorioData {
    private String parametro1;
    private List<DetalhesTabela> listaDeParametros;

    // getters e setters
}

