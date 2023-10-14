package ru.otus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.domain.Subject;

import java.util.UUID;

public interface SubjectRepository extends JpaRepository<Subject, UUID> {

    Subject findByName(String name);
}
