package ru.otus.repository;

import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import ru.otus.domain.Genre;

import java.util.Optional;

@DataJpaTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@ActiveProfiles("test")
class GenreRepositoryTest {
    private static final long NEW_GENRE_ID = 4;
    private final GenreRepository genreRepository;

    @Test
    void shouldInsertBook() {
        Genre genre = new Genre("genre");
        genreRepository.save(genre);
        Optional<Genre> actualGenre = genreRepository.findById(NEW_GENRE_ID);
        assert actualGenre.orElse(null) != null;
        Assertions.assertThat(actualGenre.orElse(null).getName()).isEqualTo(genre.getName());
    }
}
