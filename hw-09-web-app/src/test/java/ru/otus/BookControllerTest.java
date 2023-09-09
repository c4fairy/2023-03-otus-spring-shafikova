package ru.otus;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.controller.BookController;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;
import ru.otus.service.AuthorService;
import ru.otus.service.BookService;
import ru.otus.service.CommentService;
import ru.otus.service.GenreService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(BookController.class)
class BookControllerTest {

    private static final String BOOK_TITLE = "booktitle";
    private static final String AUTHOR_NAME = "authorname";
    private static final String GENRE_NAME = "genrename";
    private static final int BOOK_ID = 1;

    @MockBean
    private BookService bookService;

    @MockBean
    private CommentService commentService;

    @MockBean
    private GenreService genreService;

    @MockBean
    private AuthorService authorService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnAllBooks() throws Exception {
        List<Book> books = new ArrayList<>();
        books.add(new Book(BOOK_TITLE, new Author(AUTHOR_NAME), new Genre(GENRE_NAME)));
        given(bookService.findAll()).willReturn(books);
        mockMvc.perform(post("/")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        )
                .andExpect(redirectedUrl(null));
    }

    @Test
    void shouldAddNewBook() throws Exception {
        Book book = new Book(BOOK_ID, BOOK_TITLE, new Author(1L, AUTHOR_NAME), new Genre(1L, GENRE_NAME));
        doNothing().when(bookService).addOrSaveBook(book);
        mockMvc.perform(post("/create/book")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .sessionAttr("book", book)
        )
                .andExpect(redirectedUrl("/"))
                .andExpect(view().name("redirect:/"));
    }

    @Test
    void shouldReturnBook() throws Exception {
        Book book = new Book(BOOK_ID, BOOK_TITLE, new Author(1L, AUTHOR_NAME), new Genre(1L, GENRE_NAME));
        mockMvc.perform(get("/view/book/{book}", book))
                .andExpect(redirectedUrl(null));
    }

    @Test
    void shouldUpdateBook() throws Exception {
        Book book = new Book(BOOK_ID, BOOK_TITLE, new Author(1L, AUTHOR_NAME), new Genre(1L, GENRE_NAME));
        doNothing().when(bookService).addOrSaveBook(book);
        mockMvc.perform(post("/edit/book")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .sessionAttr("book", book)
        )
                .andExpect(redirectedUrl(null));
    }

    @Test
    void shouldDeleteBook() throws Exception {
        Book book = new Book(BOOK_ID, BOOK_TITLE, new Author(1L, AUTHOR_NAME), new Genre(1L, GENRE_NAME));
        doNothing().when(bookService).delete(book);
        mockMvc.perform(post("/delete/{book}/", book)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        )
                .andExpect(redirectedUrl(null));
    }
}