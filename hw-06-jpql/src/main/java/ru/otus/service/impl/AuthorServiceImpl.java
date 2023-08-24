package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.dao.AuthorDao;
import ru.otus.model.Author;
import ru.otus.service.AuthorService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorDao authorDao;

    @Override
    @Transactional
    public Author save(Author author) {
        return authorDao.save(author);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Author findById(long id) {
        return authorDao.findById(id).get();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Author> findAll() {
        return authorDao.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Author findByNameAndSurname(String name, String surname) {
        return authorDao.findByNameAndSurname(name, surname);
    }


}
