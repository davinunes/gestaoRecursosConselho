package br.eti.davinunes.apiconselho.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.eti.davinunes.apiconselho.entity.Recurso;
import br.eti.davinunes.apiconselho.repository.RecursoRepository;

@Service
public class RecursoService {

    private final RecursoRepository recursoRepository;

    @Autowired
    public RecursoService(RecursoRepository recursoRepository) {
        this.recursoRepository = recursoRepository;
    }

    public List<Recurso> listarRecursos() {
        return recursoRepository.findAll();
    }

    public Recurso criarRecurso(Recurso recurso) {
        return recursoRepository.save(recurso);
    }
}

