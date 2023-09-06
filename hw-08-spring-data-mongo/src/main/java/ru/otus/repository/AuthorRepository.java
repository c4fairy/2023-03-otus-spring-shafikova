package ru.otus.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.domain.Author;

public interface AuthorRepository extends MongoRepository<Author, Long> {
    Author findByName(String name);
}
