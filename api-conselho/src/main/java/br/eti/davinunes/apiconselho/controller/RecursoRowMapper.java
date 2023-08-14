package br.eti.davinunes.apiconselho.controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.eti.davinunes.apiconselho.entity.Recurso;

public class RecursoRowMapper implements RowMapper<Recurso> {

    @Override
    public Recurso mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Recurso recurso = new Recurso();
        recurso.setId(resultSet.getLong("id"));
        recurso.setNome(resultSet.getString("nome"));
        recurso.setDescricao(resultSet.getString("descricao"));
        // Mapeie os outros campos aqui
        return recurso;
    }
}
