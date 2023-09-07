package ru.otus.springdatajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.springdatajpa.model.Author;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findByNameAndSurname(String name, String surname);
}
