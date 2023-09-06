package ru.otus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.service.AuthorService;
import ru.otus.service.BookService;
import ru.otus.service.CommentService;
import ru.otus.service.ConsoleService;


import java.util.List;

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
        consoleService.write("Введите наименование книги");
        String title = consoleService.read();
        consoleService.write("Введите жанр");
        String genreName = consoleService.read();
        consoleService.write("Введите автора");
        String authorName = consoleService.read();
        bookService.addBook(title, authorName, genreName);
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
        consoleService.write("Введите id книги, которой хотите добавить комментарий");
        int bookId = consoleService.readInt();
        Book book = bookService.findById(bookId);
        if (book != null) {
            consoleService.write("Введите комментарий для книги - " + book.getTitle());
            String commentText = consoleService.read();
            bookService.addComment(bookId, commentText);
        } else {
            consoleService.write("Книги по такому ID не существует.");
        }
    }

    @ShellMethod(key = {"commentShowAll", "csha"}, value = "show all comments to book by Id")
    public void showAllCommentsToBookById() {
        consoleService.write("Введите Id книги, по которой отобразить комментарии");
        long id = consoleService.readInt();
        List<Comment> allComments = bookService.findCommentsByBookId(id);
        consoleService.write("Комментарии к книге " + bookService.findById(id).getTitle());
        if (allComments != null) {
            allComments.forEach(comment -> consoleService.write(comment.toString()));
        } else consoleService.write("У книги отсутствуют комментарии");
    }

    @ShellMethod(key = {"commentDeleteById", "cdbid"}, value = "delete comment by Id")
    public void deleteCommentById() {
        consoleService.write("Введите Id комментария, который надо удалить");
        long id = consoleService.readInt();
        commentService.deleteById(id);
    }

    @ShellMethod(key = {"bookListByAuthorId", "blai"}, value = "show all books by author id")
    public void showAllBooksByAuthorId() {
        consoleService.write("Введите Id автора для отображения списка его книг");
        long id = consoleService.readInt();
        List<Book> books = bookService.findAllBooksByAuthorId(id);
        consoleService.write("Книги автора: " + authorService.findById(id).getName());
        books.forEach(book -> consoleService.write(book.getTitle()));
    }

    @ShellMethod(key = {"commentListByAuthorId", "clbai"}, value = "show all comments to all books by author id")
    public void showAllCommentsByAuthorId() {
        consoleService.write("Введите Id автора для отображения всех комментариев к его книгам");
        long id = consoleService.readInt();
        List<Book> books = bookService.findAllBooksByAuthorId(id);
        consoleService.write("Комментарии к книгам автора: " + authorService.findById(id).getName());
        books.forEach(book -> {
            if (book.getComments() != null)
                consoleService.write("Книга: " + book.getTitle() + ". Комментарии: " + book.getComments().toString());
            else consoleService.write("Книга: " + book.getTitle() + ". Комментарии: 0");
        });
    }

    @ShellMethod(key = {"bookListWithCommentsCountGroupBy", "blwc"}, value = "show all books and comments counts")
    public void showAllBooksWithComments() {
        List<Book> books = bookService.findAll();
        books.forEach(author ->
                consoleService.write(author.toStringWithCommentCount()));
    }
}
