package ru.otus.service;


import ru.otus.domain.Author;

public interface AuthorService {
    Author getAuthor(String authorName);

    Author findById(long id);
}
