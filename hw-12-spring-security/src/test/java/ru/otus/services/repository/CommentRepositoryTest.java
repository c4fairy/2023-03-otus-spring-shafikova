package ru.otus.services.repository;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.repository.CommentRepository;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@ActiveProfiles("test")
class CommentRepositoryTest {

    private static final String NEW_COMMENT_TEXT = "new comment text";
    private static final long FIRST_BOOK_ID = 1;
    private static final long FIRST_COMMENT_ID = 1;

    private final CommentRepository commentRepository;
    private final TestEntityManager em;

    @Test
    void shouldSaveComment() {
        Book book = em.find(Book.class, FIRST_BOOK_ID);
        var comment = new Comment(FIRST_COMMENT_ID, NEW_COMMENT_TEXT, book);
        comment = commentRepository.save(comment);

        assertThat(comment.getId()).isPositive();

        val actualComment = em.find(Comment.class, comment.getId());
        assertThat(actualComment).isNotNull().matches(b -> !b.getText().equals(""))
                .matches(b -> b.getBook() != null);
    }

    @Test
    void shouldDeleteBookNameById() {
        commentRepository.deleteById(FIRST_COMMENT_ID);
        val deletedComment = em.find(Comment.class, FIRST_COMMENT_ID);
        assertThat(deletedComment).isNull();
    }
}
