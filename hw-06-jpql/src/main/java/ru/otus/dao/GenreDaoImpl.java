package ru.otus.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.model.Genre;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GenreDaoImpl implements GenreDao {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public Genre save(Genre genre) {
        if (genre.getId() == null) {
            entityManager.persist(genre);
            return genre;
        } else {
            return entityManager.merge(genre);
        }
    }

    @Override
    public Optional<Genre> findById(long id) {
        return Optional.ofNullable(entityManager.find(Genre.class, id));
    }

    @Override
    public Genre findByName(String name) {
        try {
            TypedQuery<Genre> query = entityManager.createQuery("select genre from Genre genre where genre.name=:name", Genre.class);
            query.setParameter("name", name);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
