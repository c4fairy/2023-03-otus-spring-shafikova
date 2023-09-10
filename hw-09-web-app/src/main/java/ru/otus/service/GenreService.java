package ru.otus.service;


import ru.otus.domain.Genre;

import java.util.List;

public interface GenreService {
    Genre findByName(String genreName);

    List<Genre> findAll();
}