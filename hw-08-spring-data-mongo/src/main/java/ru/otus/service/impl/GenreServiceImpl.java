package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.domain.Genre;
import ru.otus.repository.GenreRepository;
import ru.otus.service.GenreService;
import ru.otus.service.SequenceGeneratorService;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;
    private final SequenceGeneratorService sequenceGeneratorService;

    @Override
    public Genre getGenre(String genreName) {
        Genre genre = genreRepository.findByName(genreName);
        if (genre == null) {
            return genreRepository.save(new Genre(sequenceGeneratorService.generateSequence(Genre.SEQUENCE_NAME), genreName));
        } else return genre;
    }
}
