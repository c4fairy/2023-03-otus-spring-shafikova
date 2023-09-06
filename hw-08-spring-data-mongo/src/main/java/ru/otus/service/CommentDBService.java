package ru.otus.service;


import ru.otus.domain.Comment;

public interface CommentDBService {
    void delete(Comment comment);

    void save(Comment comment);
}
