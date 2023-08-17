package ru.otus.service;


import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;

import java.util.List;

public interface ConsoleService {

    void printBook(Book book);
    void printBookList(List<Book> list);
    void printAuthorList(List<Author> list);
    void printGenreList(List<Genre> list);
    String enterBookName();
    long enterBookNumber();
    String enterAuthorName();
    long enterAuthorNumber();
    String enterGenreName();
    long enterGenreNumber();
    List<Long> enterAuthors();
    List<Long> enterGenres();
}
