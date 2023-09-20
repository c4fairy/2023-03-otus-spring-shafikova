package ru.otus.dao;


import ru.otus.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {
    Author save(Author author);

    List<Author> findAll();

    Optional<Author> findById(long id);

    Author findByNameAndSurname(String name, String surname);
}
