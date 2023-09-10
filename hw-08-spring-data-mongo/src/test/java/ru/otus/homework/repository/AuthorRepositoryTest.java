package ru.otus.homework.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import ru.otus.domain.Author;
import ru.otus.repository.AuthorRepository;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
class AuthorRepositoryTest {

    private static final long AUTHOR_ID = 1;
    private static final long AUTHOR2_ID = 2;
    private static final long AUTHOR3_ID = 3;
    private static final int ACTUAL_AUTHOR_BOOK_COUNT_AGGREGATE_LIST_SIZE = 3;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    MongoOperations mongoOperations;

    @Test
    void shouldHaveCorrectAuthorsBookCount() {
        List<Author> actual = authorRepository.findAll();
        assertThat(actual).hasSize(ACTUAL_AUTHOR_BOOK_COUNT_AGGREGATE_LIST_SIZE);
        List<Author> expected = new ArrayList();
        expected.add(mongoOperations.findById(AUTHOR_ID, Author.class));
        expected.add(mongoOperations.findById(AUTHOR2_ID, Author.class));
        expected.add(mongoOperations.findById(AUTHOR3_ID, Author.class));
        assertEquals(actual, expected);
    }

    private Query searchAuthor(long id) {
        return new Query(Criteria.where("Id").is(id));
    }


}
