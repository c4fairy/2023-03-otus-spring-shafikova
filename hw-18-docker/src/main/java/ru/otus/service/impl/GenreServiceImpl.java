package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.domain.Genre;
import ru.otus.repository.GenreRepository;
import ru.otus.service.GenreService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    @Override
    public Genre findByName(String genreName) {
        return genreRepository.findByName(genreName);
    }

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }


}
