package ru.otus.repository;

import lombok.AllArgsConstructor;
import lombok.val;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AllArgsConstructor(onConstructor = @__(@Autowired))
@ActiveProfiles("test")
class BookRepositoryTest {

    private static final int EXPECTED_BOOKS_COUNT = 3;

    private static final String NEW_BOOK_TITLE = "Идиот";
    private static final long FIRST_BOOK_ID = 1;

    private final TestEntityManager em;
    private final BookRepository bookRepository;

    @Test
    void shouldReturnCorrectBookCount() {
        Assertions.assertThat(bookRepository.count()).isEqualTo(EXPECTED_BOOKS_COUNT);
    }

    @Test
    void shouldInsertBook() {
        Book book = Book.builder().id(1L)
                .title(NEW_BOOK_TITLE)
                .author(Author.builder().id(1L)
                        .name("Федор")
                        .build())
                .genre(new Genre(1L, "Роман"))
                .build();
        bookRepository.save(book);
        Optional<Book> actualBook = bookRepository.findById(1L);
        Assertions.assertThat(actualBook.orElse(null)).isEqualTo(book);
    }

    @Test
    void shouldSaveBook() {
        val author = Author.builder().id(1L)
                .name("Федор")
                .build();
        val genre = new Genre(1L, "Роман");
        var book = Book.builder().id(1L)
                .title(NEW_BOOK_TITLE)
                .genre(genre)
                .author(author)
                .build();
        book = bookRepository.save(book);

        assertThat(book.getId()).isPositive();

        val actualBook = bookRepository.findById(book.getId());
        assertThat(actualBook).isNotNull().matches(b -> !b.get().getTitle().equals(""))
                .matches(b -> b.get().getAuthor() != null)
                .matches(b -> b.get().getGenre() != null);
    }

    @Test
    void shouldFindBookById() {
        val optionalActualBook = bookRepository.findById(FIRST_BOOK_ID);
        val expectedBook = em.find(Book.class, FIRST_BOOK_ID);
        assertThat(optionalActualBook).isPresent().get().isEqualTo(expectedBook);
    }

    @Test
    void shouldReturnCorrectBookListWithGenreAndAuthor() {
        val books = bookRepository.findAll();
        assertThat(books).isNotNull().hasSize(EXPECTED_BOOKS_COUNT)
                .allMatch(book -> !book.getTitle().equals(""))
                .allMatch(book -> book.getGenre() != null)
                .allMatch(book -> book.getAuthor() != null);
    }

    @Test
    void shouldUpdateBookNameById() {
        val firstBook = bookRepository.findById(FIRST_BOOK_ID);
        String oldName = firstBook.get().getTitle();
        Book book = bookRepository.findById(FIRST_BOOK_ID).get();
        book.setTitle(NEW_BOOK_TITLE);
        bookRepository.save(book);
        val updatedBook = bookRepository.findById(FIRST_BOOK_ID);

        assertThat(updatedBook.get().getTitle()).isNotEqualTo(oldName).isEqualTo(NEW_BOOK_TITLE);
    }

    @Test
    void shouldDeleteBookNameById() {
        Book book = bookRepository.findById(FIRST_BOOK_ID).get();
        bookRepository.delete(book);
        val deletedBook = bookRepository.findById(FIRST_BOOK_ID);
        assertThat(deletedBook.isPresent()).isFalse();
    }
}
