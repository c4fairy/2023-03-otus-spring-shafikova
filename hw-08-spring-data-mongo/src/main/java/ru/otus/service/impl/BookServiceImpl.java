package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.domain.Genre;
import ru.otus.repository.BookRepository;
import ru.otus.repository.CommentRepository;
import ru.otus.service.AuthorService;
import ru.otus.service.BookService;
import ru.otus.service.GenreService;
import ru.otus.service.SequenceGeneratorService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final AuthorService authorService;
    private final GenreService genreService;
    private final SequenceGeneratorService sequenceGeneratorService;
    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;

    @Override
    public void addBook(String title, String authorName, String genreName) {
        Author author = authorService.getAuthor(authorName);
        Genre genre = genreService.getGenre(genreName);
        Book book = new Book(title, author, genre);
        bookRepository.save(book);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(long id) {
        return bookRepository.findById(id);
    }

    @Override
    public void deleteById(long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public long getCount() {
        return bookRepository.count();
    }

    @Override
    public void updateNameById(long id, String name) {
        Book book = bookRepository.findById(id);
        book.setTitle(name);
        bookRepository.save(book);
    }

    @Override
    public List<Book> findByName(String name) {
        return bookRepository.findAllByTitle(name);
    }

    @Override
    public void addComment(long bookId, String commentText) {
        Book book = bookRepository.findById(bookId);
        if (book != null) {
            Comment comment = new Comment(commentText);
            commentRepository.save(comment);
            book.setComments(addCommentToBookCommentList(book, comment));
            bookRepository.save(book);
        }
    }

    @Override
    public List<Comment> findCommentsByBookId(long id) {
        Book book = bookRepository.findById(id);
        return book.getComments();
    }

    @Override
    public List<Book> findAllBooksByAuthorId(long id) {
        return bookRepository.findAllByAuthorId(id);
    }


    private List<Comment> addCommentToBookCommentList(Book book, Comment comment) {
        List<Comment> comments;
        if (book.getComments() == null) {
            comments = new ArrayList<>();
        } else {
            comments = book.getComments();
        }
        comments.add(comment);
        return comments;
    }
}
