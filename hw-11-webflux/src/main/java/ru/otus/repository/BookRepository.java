package ru.otus.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.otus.domain.Book;

public interface BookRepository extends ReactiveMongoRepository<Book, Long> {
}