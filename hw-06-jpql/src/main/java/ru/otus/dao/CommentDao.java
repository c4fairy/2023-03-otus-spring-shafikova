package ru.otus.dao;


import ru.otus.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentDao {
    Comment save(Comment comment);

    Optional<Comment> findById(long id);

    void deleteById(Comment comment);

    List<Comment> findAllCommentsByAuthorId(long id);
}