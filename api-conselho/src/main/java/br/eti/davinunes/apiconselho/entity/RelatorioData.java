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

    public String getParametro1() {
        return parametro1;
    }
    
    public void setParametro1(String parametro1) {
        this.parametro1 = parametro1;
    }


}

