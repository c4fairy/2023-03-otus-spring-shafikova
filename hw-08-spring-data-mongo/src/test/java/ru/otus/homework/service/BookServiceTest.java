package ru.otus.homework.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;
import ru.otus.repository.BookRepository;
import ru.otus.service.impl.BookServiceImpl;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
class BookServiceTest {
    @Mock
    BookRepository bookRepository;

    @InjectMocks
    BookServiceImpl bookService;

    @BeforeAll
    public void init() {
        MockitoAnnotations.openMocks(this);
        Author author = Author.builder().name("name")
                .build();
        Genre genre = new Genre(1L, "genre");
        Book book = Book.builder()
                .id(1L)
                .title("title")
                .author(author)
                .genre(genre)
                .build();
        doReturn(book).when(bookRepository).findById(anyLong());
    }

    @Test
    void shouldReturnCorrectAuthorWithNameAndSurname() {
        Book book = bookService.findById(1L);
        assertThat(book.getId()).isEqualTo(1L);
    }
}