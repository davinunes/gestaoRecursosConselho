package br.eti.davinunes.apiconselho.entity;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "recurso")
public class Recurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String notificacao;
    private String bloco;
    private int unidade;
    private String tipo;
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    private String email;
    private String nome;

    // Getters e setters
}
