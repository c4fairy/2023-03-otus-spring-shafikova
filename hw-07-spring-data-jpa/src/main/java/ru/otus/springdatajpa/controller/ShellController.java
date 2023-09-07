package ru.otus.springdatajpa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.springdatajpa.model.Author;
import ru.otus.springdatajpa.model.Book;
import ru.otus.springdatajpa.model.Comment;
import ru.otus.springdatajpa.service.AuthorService;
import ru.otus.springdatajpa.service.BookService;
import ru.otus.springdatajpa.service.CommentService;
import ru.otus.springdatajpa.service.ConsoleService;

import java.util.List;
import java.util.Map;

@ShellComponent
@RequiredArgsConstructor
public class ShellController {
    private final BookService bookService;
    private final ConsoleService consoleService;
    private final CommentService commentService;
    private final AuthorService authorService;

    @ShellMethod(key = {"bookList", "bl"}, value = "show all books")
    public void allBooks() {
        List<Book> allBooks = bookService.findAll();
        allBooks.forEach(book -> consoleService.write(book.toString()));
    }

    @ShellMethod(key = {"bookAdd", "ba"}, value = "add book to library")
    public void addBook() {
        bookService.addNewBook();
    }

    @ShellMethod(key = {"bookGetById", "bgbi"}, value = "get book by Id")
    public void getBookById() {
        long id = consoleService.readInt();
        consoleService.write(bookService.findById(id).toString());
    }

    @ShellMethod(key = {"bookDeleteById", "bdbi"}, value = "delete book by Id")
    public void deleteBookById() {
        long id = consoleService.readInt();
        bookService.deleteById(id);
    }

    @ShellMethod(key = {"booksCount", "bc"}, value = "count of all books")
    public void bookCount() {
        consoleService.write(bookService.getCount());
    }

    @ShellMethod(key = {"bookUpdateNameById", "bunbid"}, value = "update book name by Id")
    public void updateBookNameById() {
        consoleService.write("Введите Id книги, которую необходимо изменить");
        long id = consoleService.readInt();
        consoleService.write("Введите новое название книги");
        String name = consoleService.read();
        bookService.updateNameById(id, name);
    }

    @ShellMethod(key = {"bookFindByName", "bfbn"}, value = "find book by name")
    public void findBookByName() {
        consoleService.write("Введите имя книги, которую необходимо найти");
        String name = consoleService.read();
        List<Book> allBooks = bookService.findByName(name);
        allBooks.forEach(book -> consoleService.write(book.toString()));
    }

    @ShellMethod(key = {"commentAdd", "ca"}, value = "add comment to book by Id")
    public void addCommentToBookById() {
        commentService.addNewComment();
    }

    @ShellMethod(key = {"commentShowAll", "csha"}, value = "show all comments to book by Id")
    public void showAllCommentsToBookById() {
        consoleService.write("Введите Id книги, по которой отобразить комментарии");
        long id = consoleService.readInt();
        List<Comment> allComments = commentService.findByBookId(id);
        consoleService.write("Комментарии к книге " + bookService.findById(id).getTitle());
        allComments.forEach(comment -> consoleService.write(comment.toString()));
    }

    @ShellMethod(key = {"commentDeleteById", "cdbid"}, value = "delete comment by Id")
    public void deleteCommentById() {
        consoleService.write("Введите Id комментария, который надо удалить");
        long id = consoleService.readInt();
        commentService.deleteById(id);
    }

    @ShellMethod(key = {"commentEditTextByID", "cetbid"}, value = "edit comment text by id")
    public void editCommentById() {
        consoleService.write("Введите Id комментария, который необходимо изменить");
        long id = consoleService.readInt();
        consoleService.write("Введите новый комментарий");
        String text = consoleService.read();
        commentService.updateTextById(id, text);
    }

    @ShellMethod(key = {"authorList", "al"}, value = "show all authors and count of books")
    public void showAllAuthors() {
        List<Author> authors = authorService.findAll();
        authors.forEach(author -> consoleService.write(author.toString()));
    }

    @ShellMethod(key = {"bookListByAuthorId", "blai"}, value = "show all books by author id")
    public void showAllBooksByAuthorId() {
        consoleService.write("Введите Id автора для отображения списка его книг");
        long id = consoleService.readInt();
        List<Book> books = bookService.findAllBooksByAuthorId(id);
        Author author = authorService.findById(id);
        consoleService.write("Книги автора: " + author.getName() + " " + author.getSurname());
        books.forEach(book -> consoleService.write(book.getTitle()));
    }

    @ShellMethod(key = {"bookListWithCommentsCountGroupBy", "blwc"}, value = "show all books and comments counts")
    public void showAllBooksWithComments() {
        Map<Book, Long> books = bookService.findAllBooksWithCommentsCount();
        for (Map.Entry<Book, Long> entry : books.entrySet()) {
            consoleService.write(entry.getKey().toString());
            consoleService.write("Количество комментариев: " + entry.getValue());
        }
    }
}
