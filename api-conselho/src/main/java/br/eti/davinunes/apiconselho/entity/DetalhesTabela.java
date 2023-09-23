package br.eti.davinunes.apiconselho.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class DetalhesTabela {
    private String codom;
    private String nome;
    private String sigla;
    private String certificados;
    private String temSubordinados;

    // getters e setters para cada coluna
    // ...
    // @Override
    // public String toString() {
    //     return "DetalhesTabela [Codom=" + coluna1 + ", coluna2=" + coluna2 + ", coluna3=" + coluna3 + ", coluna4=" + coluna4 + "]";
    // }
}
