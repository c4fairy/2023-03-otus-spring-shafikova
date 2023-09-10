package ru.otus.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "author_id")
    private Author author;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "genre_id")
    private Genre genre;

    public Book(long id, String title, Author author, Genre genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.id = id;
    }

    public Book(Author author, Genre genre) {
        this.author = author;
        this.genre = genre;
    }

    public Book(String title, Author author, Genre genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) return false;
        Book book = (Book) obj;

        return (this.id.equals(book.id) &&
                this.title.equals(book.title) &&
                this.author.equals(book.author) &&
                this.genre.equals(book.genre)
        );
    }

    @Override
    public String toString() {
        return id +
                " Наименование: " + title +
                ". Автор: " + author.getName() +
                ". Жанр: " + genre.getName();
    }

    public String toStringWithCommentsCount() {
        return id +
                " Наименование: " + title +
                ". Автор: " + author.getName() +
                ". Жанр: " + genre.getName();
    }

}
