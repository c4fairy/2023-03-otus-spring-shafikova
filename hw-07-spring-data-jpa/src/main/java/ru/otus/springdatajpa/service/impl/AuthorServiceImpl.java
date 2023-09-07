package ru.otus.springdatajpa.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.springdatajpa.repository.AuthorRepository;
import ru.otus.springdatajpa.model.Author;
import ru.otus.springdatajpa.service.AuthorService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Override
    public Author save(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author findById(long id) {
        return authorRepository.findById(id).orElse(null);
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author findByNameAndSurname(String name, String surname) {
        Optional<Author> optionalAuthor = authorRepository.findByNameAndSurname(name, surname);
        return optionalAuthor.orElse(null);
    }


}
