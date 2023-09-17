package ru.otus.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.otus.domain.Genre;

public interface GenreRepository extends ReactiveMongoRepository<Genre, Long> {
}
