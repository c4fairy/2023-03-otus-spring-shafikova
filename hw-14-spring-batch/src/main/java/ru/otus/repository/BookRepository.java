package ru.otus.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.domain.Book;
import java.math.BigInteger;

public interface BookRepository extends MongoRepository<Book, BigInteger>, BookRepositoryCustom {
}
