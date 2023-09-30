package ru.otus.processor;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import ru.otus.domain.Comment;
import ru.otus.dto.CommentDto;
import ru.otus.repository.BookRepository;

@Component
@RequiredArgsConstructor
public class CommentDtoProcessor implements ItemProcessor<Comment, CommentDto> {

    private final BookRepository bookRepository;

    public CommentDto process(Comment comment) throws Exception {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setText(comment.getText());
        commentDto.setBookId(bookRepository.findBookByCommentId(comment.getId()).getId());
        return commentDto;
    }

}
