package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.dao.CommentDao;
import ru.otus.model.Book;
import ru.otus.model.Comment;
import ru.otus.service.BookService;
import ru.otus.service.CommentService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentDao commentDao;

    private final BookService bookService;

    @Override
    @Transactional
    public Comment save(Comment comment) {
        return commentDao.save(comment);
    }

    @Override
    public List<Comment> findByBookId(Long bookId) {
        return commentDao.findByBookId(bookId);
    }

    @Override
    @Transactional
    public void updateTextById(long id, String text) {
        Optional<Comment> commentOptional = commentDao.findById(id);
        if (commentOptional.isPresent()) {
            Comment comment = commentOptional.get();
            comment.setText(text);
            commentDao.save(comment);
        }
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        Optional<Comment> comment = commentDao.findById(id);
        comment.ifPresent(commentDao::deleteById);
    }

    @Override
    @Transactional
    public void addNewComment(int bookId, String commentText) {
        Book book = bookService.findById(bookId);
        if (book != null) {
            Comment comment = new Comment(commentText, book);
            commentDao.save(comment);
        }
    }
}
