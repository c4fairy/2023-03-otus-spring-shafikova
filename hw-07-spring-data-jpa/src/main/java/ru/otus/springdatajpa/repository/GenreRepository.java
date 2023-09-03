package ru.otus.springdatajpa.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.springdatajpa.model.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    Genre findByName(String name);
}
