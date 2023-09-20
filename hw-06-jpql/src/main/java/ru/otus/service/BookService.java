package ru.otus.service;


import ru.otus.model.Book;

import java.util.List;
import java.util.Map;

public interface BookService {
    Book save(Book book);

    Book findById(long id);

    List<Book> findAll();

    List<Book> findByName(String name);

    void updateNameById(long id, String name);

    void deleteById(long id);

    void addNewBook(String title,
                    String genreName,
                    String authorName,
                    String authorSurname);

    long getCount();

    List<Book> findAllBooksByAuthorId(long id);

    Map<Book, Long> findAllBooksWithCommentsCount();
}
