package ru.otus.service;


import ru.otus.domain.Genre;

public interface GenreService {
    Genre findByName(String genreName);
}
