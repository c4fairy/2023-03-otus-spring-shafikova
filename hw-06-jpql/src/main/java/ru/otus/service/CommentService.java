package ru.otus.service;


import ru.otus.model.Comment;

import java.util.List;

public interface CommentService {
    Comment save(Comment comment);

    List<Comment> findByBookId(Long id);

    void updateTextById(long id, String text);

    void deleteById(long id);

    void addNewComment(int bookId, String commentText);
}
