package ru.otus.service;


import ru.otus.domain.Author;

import java.util.List;

public interface AuthorService {
    Author findByName(String authorName);

    List<Author> findAll();
}
