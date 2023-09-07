package ru.otus.springdatajpa.repository;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.otus.springdatajpa.model.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findBooksByTitle(String title);

    List<Book> findAllByAuthorId(long id);

    @Query("select new org.apache.commons.lang3.tuple.ImmutablePair (book, count(comment))" +
            "                        from Book book left join Comment comment on book.id = comment.book.id " +
            "                        group by comment.book")
    List<ImmutablePair<Book, Long>> findAllBooksWithCommentsCount();
}
