package br.eti.davinunes.apiconselho.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.eti.davinunes.apiconselho.entity.Recurso;
import br.eti.davinunes.apiconselho.service.RecursoService;

@RestController
@RequestMapping("/api/recursos")
public class RecursoController {

    private final RecursoService recursoService;

    @Autowired
    public RecursoController(RecursoService recursoService) {
        this.recursoService = recursoService;
    }

    @GetMapping
    public List<Recurso> listarRecursos() {
        return recursoService.listarRecursos();
    }

    @PostMapping
    public Recurso criarRecurso(@RequestBody Recurso recurso) {
        return recursoService.criarRecurso(recurso);
    }
}

