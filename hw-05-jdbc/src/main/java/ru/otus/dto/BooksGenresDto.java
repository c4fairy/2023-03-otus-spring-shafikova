package ru.otus.dto;

import lombok.Data;

public @Data
class BooksGenresDto {

    private final long bookId;
    private final long genreId;
}
