package ru.otus.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.dao.impl.GenreDaoImpl;
import ru.otus.model.Genre;
import ru.otus.service.impl.GenreServiceImpl;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GenreServiceTest {
    @Mock
    GenreDaoImpl genreDao;

    @InjectMocks
    GenreServiceImpl genreService;

    @BeforeAll
    public void init() {
        MockitoAnnotations.openMocks(this);
        doReturn(new Genre(1L, "genre")).when(genreDao).findByName(anyString());
        doReturn(Optional.of(new Genre(1L, "genre"))).when(genreDao).findById(anyLong());
    }

    @Test
    void shouldReturnCorrectAuthorWithNameAndSurname() {
        Genre genre = genreService.findByName("genre");
        assertThat(genre.getId()).isEqualTo(1L);
    }
}