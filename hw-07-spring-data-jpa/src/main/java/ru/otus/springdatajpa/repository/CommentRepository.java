package ru.otus.springdatajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.springdatajpa.model.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    void removeCommentById(Comment comment);

    List<Comment> findByBookId(long id);
}
