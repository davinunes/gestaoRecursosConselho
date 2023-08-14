package br.eti.davinunes.apiconselho.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.eti.davinunes.apiconselho.entity.Recurso;

public interface RecursoRepository extends JpaRepository<Recurso, Long> {
}

