package ru.otus.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.domain.Book;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, Long> {

    Book findById(long id);

    List<Book> findAllByTitle(String title);

    List<Book> findAllByAuthorId(long id);
}
