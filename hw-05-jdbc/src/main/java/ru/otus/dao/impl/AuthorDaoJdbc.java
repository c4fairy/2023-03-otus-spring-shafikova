package ru.otus.dao.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.dao.AuthorDao;
import ru.otus.domain.Author;
import ru.otus.mapper.AuthorMapper;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AuthorDaoJdbc implements AuthorDao {

    private final NamedParameterJdbcOperations jdbc;

    public AuthorDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.jdbc = namedParameterJdbcOperations;
    }

    @Override
    public int count() {
        return jdbc.getJdbcOperations().queryForObject("select count(*) from AUTHORS", Integer.class);
    }

    @Override
    public void insert(Author author) {
        Map<String, Object> params = Collections.singletonMap("name", author.getName());
        jdbc.update("insert into AUTHORS (`NAME`) values (:name)", params);
    }

    @Override
    public void update(Author author) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", author.getId());
        params.put("name", author.getName());
        jdbc.update("update AUTHORS set `NAME` = :name where ID = :id", params);
    }

    @Override
    public Author getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        try {
            return jdbc.queryForObject(
                    "select * from AUTHORS where ID = :id", params, new AuthorMapper()
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Author> getListById(List<Long> ids) {
        Map<String, Object> params = Collections.singletonMap("ids", ids);
        return jdbc.query(
                "select * from AUTHORS where ID IN (:ids)", params, new AuthorMapper()
        );
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        jdbc.update("delete from AUTHORS where ID = :id", params);
    }

    @Override
    public List<Author> getAll() {
        return jdbc.query("select * from AUTHORS", new AuthorMapper());
    }

}
