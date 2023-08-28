package ru.otus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.model.Author;
import ru.otus.model.Book;
import ru.otus.model.Comment;
import ru.otus.service.AuthorService;
import ru.otus.service.BookService;
import ru.otus.service.CommentService;
import ru.otus.service.ConsoleService;

import java.util.List;
import java.util.Map;

@ShellComponent
@RequiredArgsConstructor
public class ShellController {
    private final BookService bookService;

    private final ConsoleService ioService;

    private final CommentService commentService;

    private final AuthorService authorService;

    @ShellMethod(key = {"bookList", "bl"}, value = "show all books")
    public void allBooks() {
        List<Book> allBooks = bookService.findAll();
        allBooks.forEach(book -> ioService.write(book.toString()));
    }

    @ShellMethod(key = {"bookAdd", "ba"}, value = "add book to library")
    public void addBook() {
        ioService.write("Введите наименование книги");
        String title = ioService.read();
        ioService.write("Введите жанр");
        String genreName = ioService.read();
        ioService.write("Введите имя автора");
        String authorName = ioService.read();
        ioService.write("Введите фамилию автора");
        String authorSurname = ioService.read();
        bookService.addNewBook(title, genreName, authorName, authorSurname);
    }

    @ShellMethod(key = {"bookGetById", "bgbi"}, value = "get book by Id")
    public void getBookById() {
        long id = ioService.readInt();
        ioService.write(bookService.findById(id).toString());
    }

    @ShellMethod(key = {"bookDeleteById", "bdbi"}, value = "delete book by Id")
    public void deleteBookById() {
        long id = ioService.readInt();
        bookService.deleteById(id);
    }

    @ShellMethod(key = {"booksCount", "bc"}, value = "count of all books")
    public void bookCount() {
        ioService.write(bookService.getCount());
    }

    @ShellMethod(key = {"bookUpdateNameById", "bunbid"}, value = "update book name by Id")
    public void updateBookNameById() {
        ioService.write("Введите Id книги, которую необходимо изменить");
        long id = ioService.readInt();
        ioService.write("Введите новое название книги");
        String name = ioService.read();
        bookService.updateNameById(id, name);
    }

    @ShellMethod(key = {"bookFindByName", "bfbn"}, value = "find book by name")
    public void findBookByName() {
        ioService.write("Введите имя книги, которую необходимо найти");
        String name = ioService.read();
        List<Book> allBooks = bookService.findByName(name);
        allBooks.forEach(book -> ioService.write(book.toString()));
    }

    @ShellMethod(key = {"commentAdd", "ca"}, value = "add comment to book by Id")
    public void addCommentToBookById() {
        ioService.write("Введите id книги для добавления комментария");
        int bookId = ioService.readInt();
        ioService.write("Введите комментарий");
        String commentText = ioService.read();
        commentService.addNewComment(bookId, commentText);
    }

    @ShellMethod(key = {"commentShowAll", "csha"}, value = "show all comments to book by Id")
    public void showAllCommentsToBookById() {
        ioService.write("Введите Id книги, по которой отобразить комментарии");
        long id = ioService.readInt();
        List<Comment> allComments = commentService.findByBookId(id);
        ioService.write("Комментарии к книге " + bookService.findById(id).getTitle());
        allComments.forEach(comment -> ioService.write(comment.toString()));
    }

    @ShellMethod(key = {"commentDeleteById", "cdbid"}, value = "delete comment by Id")
    public void deleteCommentById() {
        ioService.write("Введите Id комментария, который надо удалить");
        long id = ioService.readInt();
        commentService.deleteById(id);
    }

    @ShellMethod(key = {"commentEditTextByID", "cetbid"}, value = "edit comment text by id")
    public void editCommentById() {
        ioService.write("Введите Id комментария, который необходимо изменить");
        long id = ioService.readInt();
        ioService.write("Введите новый комментарий");
        String text = ioService.read();
        commentService.updateTextById(id, text);
    }

    @ShellMethod(key = {"authorList", "al"}, value = "show all authors and count of books")
    public void showAllAuthors() {
        List<Author> authors = authorService.findAll();
        authors.forEach(author -> ioService.write(author.toString()));
    }

    @ShellMethod(key = {"bookListByAuthorId", "blai"}, value = "show all books by author id")
    public void showAllBooksByAuthorId() {
        ioService.write("Введите Id автора для отображения списка его книг");
        long id = ioService.readInt();
        List<Book> books = bookService.findAllBooksByAuthorId(id);
        if (!books.isEmpty()) {
            Author author = books.get(0).getAuthor();
            ioService.write("Книги автора: " + author.getName() + " " + author.getSurname());
            books.forEach(book -> ioService.write(book.getTitle()));
        }
        ioService.write("В базе нет книг указанного автора");
    }

    @ShellMethod(key = {"bookListWithCommentsCountGroupBy", "blwc"}, value = "show all books and comments counts")
    public void showAllBooksWithComments() {
        Map<Book, Long> books = bookService.findAllBooksWithCommentsCount();
        for (Map.Entry<Book, Long> entry : books.entrySet()) {
            ioService.write(entry.getKey().toString());
            ioService.write("Количество комментариев: " + entry.getValue());
        }
    }
}
