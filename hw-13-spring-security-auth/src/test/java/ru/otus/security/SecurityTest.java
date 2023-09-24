package ru.otus.security;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.controller.BookController;
import ru.otus.repository.UserRepository;
import ru.otus.service.AuthorService;
import ru.otus.service.BookService;
import ru.otus.service.CommentService;
import ru.otus.service.GenreService;

import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BookController.class)
@MockBeans({
        @MockBean(BookService.class),
        @MockBean(AuthorService.class),
        @MockBean(GenreService.class),
        @MockBean(CommentService.class),
        @MockBean(UserRepository.class)
})
class SecurityTest {

    @Autowired
    private MockMvc mockMvc;


        @WithMockUser(username = "user2", authorities = {"DELETED"})
    @ParameterizedTest
    @ValueSource(strings = {"/comment", "/comment/delete/1", "/comment/addcomment", "/comment/edit/1"})
    void shouldForbiddenAllCommentPages(String value) throws Exception {
        mockMvc.perform(post(value)).andExpect(status().isForbidden());
    }

    @ParameterizedTest
    @ValueSource(strings = {"/comment", "/comment/delete/1", "/comment/create/comment", "/comment/edit/1"})
    void commentParameterizedNotAuthenticated(String value) throws Exception {
        mockMvc.perform(post(value)).andExpect(unauthenticated());
    }

    @WithMockUser(username = "admin")
    @ParameterizedTest
    @ValueSource(strings = {"/comment", "/comment/delete/1", "/comment/create/comment", "/comment/edit/1"})
    void commentParameterizedAuthenticated(String value) throws Exception {
        mockMvc.perform(post(value)).andExpect(authenticated());
    }

    @WithMockUser(username = "user2", authorities = {"DELETED"})
    @ParameterizedTest
    @ValueSource(strings = {"/", "/create/book", "/delete/1", "/edit/1", "/view/1"})
    void shouldForbiddenAllPages(String value) throws Exception {
        mockMvc.perform(post(value)).andExpect(status().isForbidden());
    }

    @ParameterizedTest
    @ValueSource(strings = {"/", "/create/book", "/delete/1", "/edit/1", "/view/1"})
    void parameterizedNotAuthenticated(String value) throws Exception {
        mockMvc.perform(post(value)).andExpect(unauthenticated());
    }

    @WithMockUser(username = "admin")
    @ParameterizedTest
    @ValueSource(strings = {"/", "/create/book", "/delete/1", "/edit/1", "/view/1"})
    void parameterizedAuthenticated(String value) throws Exception {
        mockMvc.perform(post(value)).andExpect(authenticated());
    }

}
