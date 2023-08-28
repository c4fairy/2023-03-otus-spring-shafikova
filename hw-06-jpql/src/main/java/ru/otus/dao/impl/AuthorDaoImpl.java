package ru.otus.dao.impl;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.dao.AuthorDao;
import ru.otus.model.Author;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthorDaoImpl implements AuthorDao {
    @PersistenceContext
    private final EntityManager entityManager;

    @Transactional
    @Override
    public Author save(Author author) {
        if (author.getId() == null) {
            entityManager.persist(author);
            return author;
        } else {
            return entityManager.merge(author);
        }
    }

    @Override
    public Optional<Author> findById(long id) {
        return Optional.ofNullable(entityManager.find(Author.class, id));
    }

    @Override
    public List<Author> findAll() {
        TypedQuery<Author> query = entityManager.createQuery("select author from Author author", Author.class);
        return query.getResultList();
    }

    @Override
    public Author findByNameAndSurname(String name, String surname) {
        try {
            TypedQuery<Author> query = entityManager.createQuery("select author " +
                            "from Author author " +
                            "where author.name =:name and author.surname =:surname"
                    , Author.class);
            query.setParameter("name", name);
            query.setParameter("surname", surname);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}

