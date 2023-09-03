package ru.otus.springdatajpa.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.springdatajpa.repository.BookRepository;
import ru.otus.springdatajpa.model.Author;
import ru.otus.springdatajpa.model.Book;
import ru.otus.springdatajpa.model.Genre;
import ru.otus.springdatajpa.service.AuthorService;
import ru.otus.springdatajpa.service.BookService;
import ru.otus.springdatajpa.service.GenreService;
import ru.otus.springdatajpa.service.ConsoleService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class BookServiceImpl implements BookService {
    private final ConsoleService consoleService;
    private final BookRepository bookRepository;
    private final GenreService genreService;
    private final AuthorService authorService;

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book findById(long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.orElse(null);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> findByName(String name) {
        return bookRepository.findBooksByTitle(name);
    }

    @Override
    public void updateNameById(long id, String name) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setTitle(name);
            bookRepository.save(book);
        }
    }

    @Override
    public void deleteById(long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            bookRepository.delete(book);
        }
    }

    @Override
    public void addNewBook() {
        consoleService.write("Введите наименование книги");
        String title = consoleService.read();
        consoleService.write("Введите жанр");
        String genreName = consoleService.read();
        consoleService.write("Введите имя автора");
        String authorName = consoleService.read();
        consoleService.write("Введите фамилию автора");
        String authorSurname = consoleService.read();
        Author author = authorService.findByNameAndSurname(authorName, authorSurname);
        if (author == null)
            author = Author.builder()
                    .name(authorName)
                    .surname(authorSurname)
                    .build();
        Genre genre = genreService.findByName(genreName);
        if (genre == null) genre = new Genre(genreName);
        Book book = Book.builder()
                .title(title)
                .genre(genre)
                .author(author)
                .build();
        bookRepository.save(book);
    }

    @Override
    public long getCount() {
        return bookRepository.count();
    }

    @Override
    public List<Book> findAllBooksByAuthorId(long id) {
        return bookRepository.findAllByAuthorId(id);
    }

    @Override
    public Map<Book, Long> findAllBooksWithCommentsCount() {
        List<ImmutablePair<Book, Long>> pairList = bookRepository.findAllBooksWithCommentsCount();
        Map<Book, Long> bookMap = new HashMap<>();
        for (ImmutablePair<Book, Long> pair : pairList)
            bookMap.put(pair.left, pair.right);
        return bookMap;
    }

}