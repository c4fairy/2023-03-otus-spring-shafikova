package ru.otus.repository;

import ru.otus.domain.Book;
import java.math.BigInteger;

public interface BookRepositoryCustom {
    Book findBookByCommentId(BigInteger Id);
}
