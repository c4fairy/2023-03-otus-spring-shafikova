package ru.otus.service;


import ru.otus.domain.Book;
import ru.otus.domain.Comment;

import java.util.List;

public interface CommentService {


    List<Comment> findAllComments(Book book);

    void deleteComment(Comment comment);

    void addOrSaveComment(Comment comment);
}
