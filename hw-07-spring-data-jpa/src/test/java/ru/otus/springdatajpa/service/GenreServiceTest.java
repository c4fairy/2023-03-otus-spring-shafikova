package ru.otus.springdatajpa.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import ru.otus.springdatajpa.repository.GenreRepository;
import ru.otus.springdatajpa.model.Genre;
import ru.otus.springdatajpa.service.impl.GenreServiceImpl;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
class GenreServiceTest {
    @Mock
    GenreRepository genreRepository;

    @InjectMocks
    GenreServiceImpl genreService;

    @BeforeAll
    public void init() {
        MockitoAnnotations.openMocks(this);
        doReturn(new Genre(1L, "genre")).when(genreRepository).findByName(anyString());
        doReturn(Optional.of(new Genre(1L, "genre"))).when(genreRepository).findById(anyLong());
    }

    @Test
    void shouldReturnCorrectAuthorWithNameAndSurname() {
        Genre genre = genreService.findByName("genre");
        assertThat(genre.getId()).isEqualTo(1L);
    }
}