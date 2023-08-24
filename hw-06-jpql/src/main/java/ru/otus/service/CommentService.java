package ru.otus.service;


import ru.otus.model.Comment;

import java.util.List;

public interface CommentService {
    Comment save(Comment comment);

    List<Comment> findByBookId(long id);

    void updateTextById(long id, String text);

    void deleteById(long id);

    void addNewComment(int bookId, String commentText);

    List<Comment> findAllCommentsByAuthorId(long id);
}
