package ru.otus.homework.repository;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import ru.otus.domain.Book;
import ru.otus.repository.BookRepository;
import ru.otus.repository.CommentRepository;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@ComponentScan({"ru.otus.repository"})
class BookRepositoryTest {
    private static final int CORRECT_COMMENTS_COUNT_AFTER_DELETE = 1;
    private static final long COMMENT_ID = 1;
    private static final long BOOK_ID = 1;
    private static final int CORRECT_BOOKS_COUNT = 5;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private MongoOperations mongoOperations;

    @Test
    void shouldReturnCorrectBookList() {
        val books = bookRepository.findAll();
        assertThat(books).isNotNull().hasSize(CORRECT_BOOKS_COUNT);
    }

    @Test
    void shouldCorrectDeleteCommentById() {
        commentRepository.deleteById(COMMENT_ID);
        assertThat(Objects.requireNonNull(mongoOperations.findOne(searchBook(BOOK_ID), Book.class)).getComments()).hasSize(CORRECT_COMMENTS_COUNT_AFTER_DELETE);
    }

    private Query searchBook(long id) {
        return new Query(Criteria.where("Id").is(id));
    }

}
