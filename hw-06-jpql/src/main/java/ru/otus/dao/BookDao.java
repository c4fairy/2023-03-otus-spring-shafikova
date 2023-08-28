package ru.otus.dao;


import org.apache.commons.lang3.tuple.ImmutablePair;
import ru.otus.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    Book save(Book book);

    Optional<Book> findById(long id);

    List<Book> findAll();

    List<Book> findByName(String name);

    void deleteBook(Book book);

    long getCount();

    List<Book> findAllBooksByAuthorId(long id);

    List<ImmutablePair<Book, Long>> findAllBooksWithCommentsCount();
}
