package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.otus.domain.Book;
import ru.otus.repository.BookRepository;
import ru.otus.service.BookDBService;
import ru.otus.service.SequenceGeneratorService;

import java.util.List;

@Service
@Qualifier("bookDBService")
@RequiredArgsConstructor
public class BookDBServiceImpl implements BookDBService {
    private final SequenceGeneratorService sequenceGeneratorService;
    private final BookRepository bookRepository;

    @Override
    public void save(Book book) {
        book.setId(sequenceGeneratorService.generateSequence(Book.SEQUENCE_NAME));
        bookRepository.save(book);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(long id) {
        return bookRepository.findById(id);
    }

    @Override
    public void deleteById(long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public long count() {
        return bookRepository.count();
    }

    @Override
    public List<Book> findAllByTitle(String name) {
        return bookRepository.findAllByTitle(name);
    }

    @Override
    public List<Book> findAllByAuthorId(long id) {
        return bookRepository.findAllByAuthorId(id);
    }
}
