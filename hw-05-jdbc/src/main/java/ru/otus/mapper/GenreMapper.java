package ru.otus.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.otus.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreMapper implements RowMapper<Genre> {

    @Override
    public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
        long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        Genre result = new Genre(id, name);
        return result;
    }
}
