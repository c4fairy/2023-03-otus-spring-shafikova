package ru.otus.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private Author author;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
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
}
