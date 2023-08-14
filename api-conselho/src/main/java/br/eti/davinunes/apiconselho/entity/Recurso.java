package br.eti.davinunes.apiconselho.entity;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "recurso")
public class Recurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String notificacao;
    private String bloco;
    private int unidade;
    private String tipo;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    private String email;
    private String nome;

    // Getters e setters
}
