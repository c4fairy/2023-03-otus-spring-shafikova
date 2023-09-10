package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;
import ru.otus.repository.BookRepository;
import ru.otus.service.AuthorService;
import ru.otus.service.BookService;
import ru.otus.service.GenreService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final GenreService genreService;

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Transactional
    @Override
    public void addBook(Book book) {
        addOrUpdateBook(book);
    }

    @Override
    public Book findById(long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public boolean update(long id, Book book) {
        if (bookRepository.findById(id).orElse(null) != null) {
            addOrUpdateBook(book);
            return true;
        } else return false;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        bookRepository.deleteBookById(id);
    }

    private void addOrUpdateBook(Book book) {
        Author author = authorService.findByName(book.getAuthor().getName());
        if (author == null) author = new Author(book.getAuthor().getName());
        Genre genre = genreService.findByName(book.getGenre().getName());
        if (genre == null) genre = new Genre(book.getGenre().getName());
        book.setAuthor(author);
        book.setGenre(genre);
        bookRepository.save(book);
    }


}
