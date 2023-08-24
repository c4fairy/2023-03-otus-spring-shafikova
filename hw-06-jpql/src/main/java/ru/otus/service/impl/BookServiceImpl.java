package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.dao.BookDao;
import ru.otus.model.Author;
import ru.otus.model.Book;
import ru.otus.model.Genre;
import ru.otus.service.AuthorService;
import ru.otus.service.BookService;
import ru.otus.service.GenreService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookDao bookDao;

    private final GenreService genreService;

    private final AuthorService authorService;

    @Override
    @Transactional
    public Book save(Book book) {
        return bookDao.save(book);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Book findById(long id) {
        Optional<Book> book = bookDao.findById(id);
        return book.orElse(null);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Book> findAll() {
        return bookDao.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Book> findByName(String name) {
        return bookDao.findByName(name);
    }

    @Override
    @Transactional
    public void updateNameById(long id, String name) {
        Optional<Book> optionalBook = bookDao.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setTitle(name);
            bookDao.save(book);
        }
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        Optional<Book> optionalBook = bookDao.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            bookDao.deleteById(book);
        }
    }

    @Override
    @Transactional
    public void addNewBook(String title,
                           String genreName,
                           String authorName,
                           String authorSurname) {
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
        bookDao.save(book);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public long getCount() {
        return bookDao.getCount();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Book> findAllBooksByAuthorId(long id) {
        return bookDao.findAllBooksByAuthorId(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Book> findAllWithComments() {
        return bookDao.findAllWithComments();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Map<Book, Long> findAllBooksWithCommentsCount() {
        List<ImmutablePair<Book, Long>> pairList = bookDao.findAllBooksWithCommentsCount();
        Map<Book, Long> bookMap = new HashMap<>();
        for (ImmutablePair<Book, Long> pair : pairList)
            bookMap.put(pair.left, pair.right);
        return bookMap;
    }

}