package ru.otus.repository;

import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import ru.otus.domain.Subject;

import java.util.Optional;
import java.util.UUID;

@DataJpaTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@ActiveProfiles("test")
class SubjectRepositoryTest {
    private static final UUID NEW_SUBJECT_ID = UUID.randomUUID();
    private final SubjectRepository subjectRepository;

    @Test
    void shouldInsertBook() {
        Subject subject = new Subject(NEW_SUBJECT_ID, "subject");
        subjectRepository.save(subject);
        Optional<Subject> actualSubject = subjectRepository.findById(NEW_SUBJECT_ID);
        assert actualSubject.orElse(null) != null;
        Assertions.assertThat(actualSubject.orElse(null).getName()).isEqualTo(subject.getName());
    }
}
