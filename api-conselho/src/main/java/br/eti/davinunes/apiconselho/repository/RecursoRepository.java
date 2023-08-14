package br.eti.davinunes.apiconselho.repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.eti.davinunes.apiconselho.controller.RecursoRowMapper;
import br.eti.davinunes.apiconselho.entity.Recurso;

@Repository
public class RecursoRepository {

    private final JdbcTemplate jdbcTemplate;

    public RecursoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Recurso> findAll() {
        String query = "SELECT * FROM recurso";
        return jdbcTemplate.query(query, new RecursoRowMapper());
    }

    public Recurso save(Recurso recurso) {
        return null;
    }
}

