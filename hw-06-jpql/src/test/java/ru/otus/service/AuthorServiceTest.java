package ru.otus.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.dao.AuthorDaoImpl;
import ru.otus.model.Author;
import ru.otus.service.impl.AuthorServiceImpl;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AuthorServiceTest {
    @Mock
    AuthorDaoImpl authorDao;

    @InjectMocks
    AuthorServiceImpl authorService;

    @BeforeAll
    public void init() {
        MockitoAnnotations.openMocks(this);
        doReturn(Author.builder().id(1L)
                .name("name")
                .surname("surname")
                .build()).when(authorDao).findByNameAndSurname(anyString(), anyString());
        doReturn(Optional.of(Author.builder().id(1L)
                .name("name")
                .surname("surname")
                .build())).when(authorDao).findById(anyLong());
    }

    @Test
    void shouldReturnCorrectAuthorWithNameAndSurname() {
        Author author = authorService.findByNameAndSurname("name", "surname");
        assertThat(author.getId()).isEqualTo(1L);
    }
}