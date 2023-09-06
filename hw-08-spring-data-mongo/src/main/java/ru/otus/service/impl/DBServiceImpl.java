package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.service.BookDBService;
import ru.otus.service.CommentDBService;
import ru.otus.service.DBService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DBServiceImpl implements DBService {

    private final BookDBService bookDBService;
    private final CommentDBService commentDBService;


    @Override
    public void save(Book book) {
        bookDBService.save(book);
    }

    @Override
    public List<Book> findAll() {
        return bookDBService.findAll();
    }

    @Override
    public Book findById(long id) {
        return bookDBService.findById(id);
    }

    @Override
    public void delete(Comment comment) {
        commentDBService.delete(comment);
    }

    @Override
    public void deleteById(long id) {
        bookDBService.deleteById(id);
    }

    @Override
    public long count() {
        return bookDBService.count();
    }

    @Override
    public List<Book> findAllByTitle(String name) {
        return bookDBService.findAllByTitle(name);
    }

    @Override
    public void save(Comment comment) {
        commentDBService.save(comment);
    }

    @Override
    public List<Book> findAllByAuthorId(long id) {
        return bookDBService.findAllByAuthorId(id);
    }

}
