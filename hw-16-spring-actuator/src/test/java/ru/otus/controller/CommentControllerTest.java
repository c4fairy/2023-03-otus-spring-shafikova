package ru.otus.controller;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.domain.Genre;
import ru.otus.service.BookService;
import ru.otus.service.CommentService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CommentController.class)
public class CommentControllerTest {

    private static final String BOOK_TITLE = "booktitle";
    private static final String AUTHOR_NAME = "authorname";
    private static final String GENRE_NAME = "genrename";
    private static final long ID = 1;

    @MockBean
    private BookService bookService;

    @MockBean
    private CommentService commentService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldAddNewComment() throws Exception {
        Book book = new Book(BOOK_TITLE, new Author(AUTHOR_NAME), new Genre(GENRE_NAME));
        Comment comment = new Comment(ID, "great book", book);
        Gson gson = new Gson();
        String json = gson.toJson(comment);
        mockMvc.perform(post("/comment/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());
    }


    @Test
    void shouldDeleteComment() throws Exception {
        mockMvc.perform(delete("/comment/1"))
                .andExpect(status().isOk());
    }


}