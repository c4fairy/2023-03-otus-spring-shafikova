package ru.otus.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;

import java.util.List;

@AllArgsConstructor
@Data
public class BookCommentsDto {
    Book book;
    List<Comment> comments;

}
