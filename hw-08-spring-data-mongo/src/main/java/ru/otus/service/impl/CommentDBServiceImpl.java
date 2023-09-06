package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.otus.domain.Comment;
import ru.otus.repository.CommentRepository;
import ru.otus.service.CommentDBService;
import ru.otus.service.SequenceGeneratorService;

@Service
@Qualifier("commentDBService")
@RequiredArgsConstructor
public class CommentDBServiceImpl implements CommentDBService {
    private final SequenceGeneratorService sequenceGeneratorService;
    private final CommentRepository commentRepository;

    @Override
    public void delete(Comment comment) {
        commentRepository.delete(comment);
    }

    @Override
    public void save(Comment comment) {
        comment.setId(sequenceGeneratorService.generateSequence(Comment.SEQUENCE_NAME));
        commentRepository.save(comment);
    }
}
