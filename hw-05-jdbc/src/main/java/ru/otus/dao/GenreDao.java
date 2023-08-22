package ru.otus.dao;


import ru.otus.domain.Genre;

import java.util.List;

public interface GenreDao {

    int count();

    List<Genre> getAll();

    void insert(Genre author);

    void update(Genre author);

    Genre getById(long id);

    List<Genre> getListById(List<Long> ids);

    void deleteById(long id);
}
