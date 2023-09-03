package ru.otus.springdatajpa.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.springdatajpa.repository.GenreRepository;
import ru.otus.springdatajpa.model.Genre;
import ru.otus.springdatajpa.service.GenreService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    @Override
    public Genre save(Genre genre) {
        return genreRepository.save(genre);
    }

    @Override
    public Optional<Genre> findById(long id) {
        return genreRepository.findById(id);
    }


    @Override
    public Genre findByName(String name) {
        return genreRepository.findByName(name);
    }
}


