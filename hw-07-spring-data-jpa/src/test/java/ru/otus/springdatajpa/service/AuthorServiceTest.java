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
import ru.otus.springdatajpa.repository.AuthorRepository;
import ru.otus.springdatajpa.model.Author;
import ru.otus.springdatajpa.service.impl.AuthorServiceImpl;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
class AuthorServiceTest {
    @Mock
    AuthorRepository authorRepository;

    @InjectMocks
    AuthorServiceImpl authorService;

    @BeforeAll
    public void init() {
        MockitoAnnotations.openMocks(this);
        doReturn(Optional.of(Author.builder().id(1L)
                .name("name")
                .surname("surname")
                .build())).when(authorRepository).findByNameAndSurname(anyString(), anyString());
        doReturn(Optional.of(Author.builder().id(1L)
                .name("name")
                .surname("surname")
                .build())).when(authorRepository).findById(anyLong());
    }

    @Test
    void shouldReturnCorrectAuthorWithNameAndSurname() {
        Optional<Author> author = Optional.ofNullable(authorService.findByNameAndSurname("name", "surname"));
        assertThat(author.get().getId()).isEqualTo(1L);
    }
}