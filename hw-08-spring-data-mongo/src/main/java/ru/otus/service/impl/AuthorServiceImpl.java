package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.domain.Author;
import ru.otus.repository.AuthorRepository;
import ru.otus.service.AuthorService;
import ru.otus.service.SequenceGeneratorService;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final SequenceGeneratorService sequenceGeneratorService;

    @Override
    public Author getAuthor(String authorName) {
        Author author = authorRepository.findByName(authorName);
        if (author == null) {
            return authorRepository.save(new Author(sequenceGeneratorService.generateSequence(Author.SEQUENCE_NAME), authorName));
        } else return author;
    }

    @Override
    public Author findById(long id) {
        return authorRepository.findById(id).orElse(null);
    }
}
