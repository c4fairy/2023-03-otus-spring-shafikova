package ru.otus.springdatajpa.service;


import ru.otus.springdatajpa.model.Comment;

import java.util.List;

public interface CommentService {
    Comment save(Comment comment);

    List<Comment> findByBookId(long id);

    void updateTextById(long id, String text);

    void deleteById(long id);

    void addNewComment();
}
