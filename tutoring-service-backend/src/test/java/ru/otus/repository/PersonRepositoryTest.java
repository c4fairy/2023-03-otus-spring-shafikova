package ru.otus.repository;

import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import ru.otus.domain.Person;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@DataJpaTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@ActiveProfiles("test")
class PersonRepositoryTest {
    private final PersonRepository personRepository;

    @Test
    void shouldInsertAuthor() {
        Person person = Person.builder()
                .id(UUID.randomUUID())
                .fio("Some FIO")
                .email("email")
                .tutorFlag(false)
                .balance(BigDecimal.valueOf(0.00))
                .build();
        personRepository.save(person);
        Optional<Person> actualPerson = personRepository.findByFio("Some FIO");
        Assertions.assertThat(actualPerson.get().getFio()).isEqualTo(person.getFio());
    }
}
