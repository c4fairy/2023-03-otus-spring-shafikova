package ru.otus.service;


import ru.otus.domain.Book;

import java.util.List;

public interface BookService {

    List<Book> findAll();

    void addBook(Book book);

    Book findById(long id);

    boolean update(long id, Book book);

    void deleteById(Long id);
}
