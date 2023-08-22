package ru.otus;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.dao.impl.BookDaoJdbc;
import ru.otus.domain.Book;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий на основе Jdbc для работы с книгами ")
@JdbcTest
@Import({BookDaoJdbc.class})
class BookDaoJdbcTest {

    private static final int EXPECTED_NUMBER_OF_BOOKS = 3;

    @Autowired
    private BookDaoJdbc repositoryJdbc;

    @DisplayName("должен уметь считать количество всех книг в БД")
    @Test
    void shouldReturnCountBooks() {
        val count = repositoryJdbc.count();
        assertThat(count).isEqualTo(EXPECTED_NUMBER_OF_BOOKS);
    }

    @DisplayName("должен загружать список всех книг с полной информацией о них")
    @Test
    void shouldReturnCorrectBooksListWithAllInfo() {
        val books = repositoryJdbc.getAllFull();
        assertThat(books).isNotNull().hasSize(EXPECTED_NUMBER_OF_BOOKS)
                .allMatch(b -> !b.getName().equals(""))
                .allMatch(b -> b.getAuthors() != null && b.getAuthors().size() > 0)
                .allMatch(b -> b.getGenres() != null && b.getGenres().size() > 0);
    }

    @DisplayName("должен загружать список всех книг без полной информации о них")
    @Test
    void shouldReturnCorrectBooksListWithoutAllInfo() {
        val books = repositoryJdbc.getAll();
        assertThat(books).isNotNull().hasSize(EXPECTED_NUMBER_OF_BOOKS)
                .allMatch(b -> !b.getName().equals(""))
                .allMatch(b -> b.getAuthors() != null && b.getAuthors().isEmpty())
                .allMatch(b -> b.getGenres() != null && b.getGenres().isEmpty());
    }

    @DisplayName("должен удалять книгу")
    @Test
    void shouldDeleteBook() {
        repositoryJdbc.deleteById(4);
        Book book = repositoryJdbc.getById(4);
        assertThat(book).isNull();
    }
}
