package ru.otus.service;


import ru.otus.model.Genre;

import java.util.Optional;

public interface GenreService {
    Genre save(Genre genre);

    Optional<Genre> findById(long id);


    Genre findByName(String name);


}
