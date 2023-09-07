package ru.otus.springdatajpa.repository;

import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import ru.otus.springdatajpa.model.Author;

import java.util.Optional;

@DataJpaTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@ActiveProfiles("test")
class AuthorRepositoryTest {
    private final AuthorRepository authorRepository;

    @Test
    void shouldInsertAuthor() {
        Author author = Author.builder()
                .id(1L)
                .name("name")
                .surname("surname")
                .build();
        authorRepository.save(author);
        Optional<Author> actualAuthor = authorRepository.findByNameAndSurname("name", "surname");
        Assertions.assertThat(actualAuthor.get().getName()).isEqualTo(author.getName());
        Assertions.assertThat(actualAuthor.get().getSurname()).isEqualTo(author.getSurname());
    }
}
