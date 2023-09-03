package ru.otus.springdatajpa.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.springdatajpa.repository.CommentRepository;
import ru.otus.springdatajpa.model.Book;
import ru.otus.springdatajpa.model.Comment;
import ru.otus.springdatajpa.service.BookService;
import ru.otus.springdatajpa.service.CommentService;
import ru.otus.springdatajpa.service.ConsoleService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final ConsoleService consoleService;
    private final BookService bookService;

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> findByBookId(long id) {
        return commentRepository.findByBookId(id);
    }

    @Override
    public void updateTextById(long id, String text) {
        Optional<Comment> commentOptional = commentRepository.findById(id);
        if (commentOptional.isPresent()) {
            Comment comment = commentOptional.get();
            comment.setText(text);
            commentRepository.save(comment);
        }

    }

    @Override
    public void deleteById(long id) {
        Optional<Comment> comment = commentRepository.findById(id);
        comment.ifPresent(commentRepository::removeCommentById);
    }

    @Override
    public void addNewComment() {
        consoleService.write("Введите id книги для добавления комментария");
        int bookId = consoleService.readInt();
        Book book = bookService.findById(bookId);
        if (book != null) {
            consoleService.write("Введите комментарий - " + book.getTitle());
            String commentText = consoleService.read();
            Comment comment = new Comment(commentText, book);
            commentRepository.save(comment);
        } else {
            consoleService.write("Книга не найдена");
        }
    }
}
