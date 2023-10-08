package ru.otus.service;

import ru.otus.domain.Book;
import ru.otus.domain.Comment;

import java.util.List;

public interface CachedDataService {
    List<Book> getCachedBooks();

    Book getCachedBook();

    List<Comment> getCachedComments();
}
