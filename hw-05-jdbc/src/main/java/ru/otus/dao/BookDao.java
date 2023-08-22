package ru.otus.dao;

import ru.otus.domain.Book;

import java.util.List;

public interface BookDao {

    int count();

    void insert(Book book);

    void update(Book book);

    Book getById(long id);

    List<Book> getAll();

    List<Book> getAllFull();

    void deleteById(long id);
}
