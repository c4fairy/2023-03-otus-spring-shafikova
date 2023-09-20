package ru.otus.dao;

import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.dao.impl.AuthorDaoImpl;
import ru.otus.model.Author;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(AuthorDaoImpl.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class AuthorDaoImplTest {
    private final AuthorDaoImpl authorDao;

    @Test
    void shouldInsertAuthor() {
        Author author = Author.builder()
                .id(1L)
                .name("name")
                .surname("surname")
                .build();
        authorDao.save(author);
        Author actualAuthor = authorDao.findByNameAndSurname("name", "surname");
        Assertions.assertThat(actualAuthor.getName()).isEqualTo(author.getName());
        Assertions.assertThat(actualAuthor.getSurname()).isEqualTo(author.getSurname());
    }

}
