package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.domain.Genre;
import ru.otus.service.AuthorService;
import ru.otus.service.BookService;
import ru.otus.service.DBService;
import ru.otus.service.GenreService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final AuthorService authorService;
    private final GenreService genreService;
    private final DBService dbService;

    @Override
    public void addBook(String title, String authorName, String genreName) {
        Author author = authorService.getAuthor(authorName);
        Genre genre = genreService.getGenre(genreName);
        Book book = new Book(title, author, genre);
        dbService.save(book);
    }

    @Override
    public List<Book> findAll() {
        return dbService.findAll();
    }

    @Override
    public Book findById(long id) {
        return dbService.findById(id);
    }

    @Override
    public void deleteById(long id) {
        dbService.findById(id).getComments().forEach(dbService::delete);
        dbService.deleteById(id);
    }

    @Override
    public long getCount() {
        return dbService.count();
    }

    @Override
    public void updateNameById(long id, String name) {
        Book book = dbService.findById(id);
        book.setTitle(name);
        dbService.save(book);
    }

    @Override
    public List<Book> findByName(String name) {
        return dbService.findAllByTitle(name);
    }

    @Override
    public void addComment(long bookId, String commentText) {
        Book book = dbService.findById(bookId);
        if (book != null) {
            Comment comment = new Comment(commentText);
            dbService.save(comment);
            book.setComments(addCommentToBookCommentList(book, comment));
            dbService.save(book);
        }
    }

    @Override
    public List<Comment> findCommentsByBookId(long id) {
        Book book = dbService.findById(id);
        return book.getComments();
    }

    @Override
    public List<Book> findAllBooksByAuthorId(long id) {
        return dbService.findAllByAuthorId(id);
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
