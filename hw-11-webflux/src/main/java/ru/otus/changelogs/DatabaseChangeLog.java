package ru.otus.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.impl.MongockTemplate;
import lombok.RequiredArgsConstructor;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.domain.Genre;

import java.util.ArrayList;
import java.util.List;

@ChangeLog
@RequiredArgsConstructor
public class DatabaseChangeLog {
    @ChangeSet(order = "001", id = "addBooks", author = "Dmitriy")
    public void insertBooks(MongockTemplate mongoTemplate) {
        Genre genre1 = new Genre("genre1");
        Genre genre2 = new Genre("genre2");
        Author author1 = new Author("author1");
        Author author2 = new Author("author2");
        Author author3 = new Author("author3");
        Comment comment1 = new Comment("new comment 1");
        Comment comment2 = new Comment("comment 2");
        Comment comment3 = new Comment("comment 3");
        Comment comment4 = new Comment("comment 4");
        List<Comment> list1 = new ArrayList();
        List<Comment> list2 = new ArrayList();
        list1.add(comment1);
        list1.add(comment2);
        list2.add(comment3);
        list2.add(comment4);

        Book book1 = new Book(1L, "book1", author1, genre1, list1);
        Book book2 = new Book(2L, "book2", author1, genre2, list2);
        Book book3 = new Book(3L, "book3", author2, genre1, null);
        Book book4 = new Book(4L, "book4", author2, genre2, null);
        Book book5 = new Book(5L, "book5", author3, genre2, null);

        List<Book> author1books = new ArrayList<>();
        author1books.add(book1);
        author1books.add(book2);
        List<Book> author2books = new ArrayList<>();
        author2books.add(book3);
        author2books.add(book4);
        List<Book> author3books = new ArrayList<>();
        author3books.add(book5);


        mongoTemplate.save(genre1);
        mongoTemplate.save(genre2);
        mongoTemplate.save(author1);
        mongoTemplate.save(author2);
        mongoTemplate.save(author3);
        mongoTemplate.save(comment1);
        mongoTemplate.save(comment2);
        mongoTemplate.save(comment3);
        mongoTemplate.save(comment4);
        mongoTemplate.save(book1);
        mongoTemplate.save(book2);
        mongoTemplate.save(book3);
        mongoTemplate.save(book4);
        mongoTemplate.save(book5);
    }
}
