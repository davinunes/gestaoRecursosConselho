package br.eti.davinunes.apiconselho.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class DetalhesTabela {
    private String coluna1;
    private String coluna2;
    private String coluna3;
    private String coluna4;

    // getters e setters para cada coluna
    // ...
    @Override
    public String toString() {
        return "DetalhesTabela [coluna1=" + coluna1 + ", coluna2=" + coluna2 + ", coluna3=" + coluna3 + ", coluna4=" + coluna4 + "]";
    }
}
