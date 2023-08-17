package ru.otus.dao;


import ru.otus.domain.Author;

import java.util.List;

public interface AuthorDao {

    int count();

    List<Author> getAll();

    void insert(Author author);

    void update(Author author);

    Author getById(long id);

    List<Author> getListById(List<Long> ids);

    void deleteById(long id);
}
