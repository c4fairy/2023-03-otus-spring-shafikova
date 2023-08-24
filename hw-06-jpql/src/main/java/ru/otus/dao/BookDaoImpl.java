package ru.otus.dao;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Component;
import ru.otus.model.Book;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BookDaoImpl implements BookDao {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public Book save(Book book) {
        return entityManager.merge(book);
    }

    @Override
    public Optional<Book> findById(long id) {
        return Optional.ofNullable(entityManager.find(Book.class, id));
    }

    @Override
    public List<Book> findAll() {
        EntityGraph<?> entityGraph = entityManager.getEntityGraph("author_genre_entity_graph");
        TypedQuery<Book> query = entityManager.createQuery("select book " +
                "from Book book", Book.class);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();
    }

    @Override
    public List<Book> findByName(String title) {
        EntityGraph<?> entityGraph = entityManager.getEntityGraph("author_genre_entity_graph");
        TypedQuery<Book> query = entityManager.createQuery("select book " +
                "from Book book " +
                "where book.title=:title", Book.class);
        query.setParameter("title", title);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();
    }

    @Override
    public void deleteById(Book book) {
        entityManager.remove(book);
    }

    @Override
    public long getCount() {
        return entityManager.createQuery("select count(book) from Book book", Long.class).getSingleResult();
    }

    @Override
    public List<Book> findAllBooksByAuthorId(long id) {
        EntityGraph<?> entityGraph = entityManager.getEntityGraph("author_genre_entity_graph");
        TypedQuery<Book> query = entityManager.createQuery("select book from Book book where book.author.id=:id", Book.class);
        query.setParameter("id", id);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();
    }

    @Override
    public List<Book> findAllWithComments() {
        return null;
    }

    @Override
    public List<ImmutablePair<Book, Long>> findAllBooksWithCommentsCount() {
        Query query = entityManager.createQuery(
                "select new org.apache.commons.lang3.tuple.ImmutablePair (comment.book, count(comment)) " +
                        "from Comment comment " +
                        "group by comment.book");
        return query.getResultList();
    }

}
