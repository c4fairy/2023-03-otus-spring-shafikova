package ru.otus;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.dao.impl.GenreDaoJdbc;
import ru.otus.domain.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий на основе Jdbc для работы с жанрами ")
@JdbcTest
@Import({GenreDaoJdbc.class})
class GenreDaoJdbcTest {

    private static final int EXPECTED_NUMBER_OF_GENRES = 3;

    @Autowired
    private GenreDaoJdbc repositoryJdbc;

    @DisplayName("должен уметь считать количество всех жанров в БД")
    @Test
    void shouldReturnCountGenres() {
        val count = repositoryJdbc.count();
        assertThat(count).isEqualTo(EXPECTED_NUMBER_OF_GENRES);
    }

    @DisplayName("должен загружать список всех жанров")
    @Test
    void shouldReturnCorrectGenresList() {
        val genres = repositoryJdbc.getAll();
        assertThat(genres).isNotNull().hasSize(EXPECTED_NUMBER_OF_GENRES)
                .allMatch(g -> !g.getName().equals(""));
    }

    @DisplayName("должен загружать ровно 1 жанр")
    @Test
    void shouldReturnOneGenre() {
        Genre genre = repositoryJdbc.getById(3);
        assertThat(genre).isNotNull()
                .matches(g -> !g.getName().equals(""));
    }

    @DisplayName("должен добавлять ровно 1 жанр")
    @Test
    void shouldAddOneGenre() {
        Genre genre = new Genre(4, "Entertainment");
        repositoryJdbc.insert(genre);
        Genre genreFromDB = repositoryJdbc.getById(4);
        assertThat(genre).isEqualTo(genreFromDB);
    }

    @DisplayName("должен изменять имя жанра")
    @Test
    void shouldEditGenreName() {
        Genre genre = new Genre(3, "Test Driven development");
        repositoryJdbc.update(genre);
        genre = repositoryJdbc.getById(3);
        assertThat(genre).isNotNull()
                .matches(g -> g.getName().equals("Test Driven development"));
    }

    @DisplayName("должен удалять жанр")
    @Test
    void shouldDeleteGenre() {
        repositoryJdbc.deleteById(3);
        Genre genre = repositoryJdbc.getById(3);
        assertThat(genre).isNull();
    }
}