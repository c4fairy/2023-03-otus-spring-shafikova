package ru.otus.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigInteger;

@Data
@NoArgsConstructor
@Document(collection = "author")
public class Author {
    @Transient
    public static final String SEQUENCE_NAME = "authors_sequence";
    @Id
    private BigInteger id;
    private String name;


    public Author(BigInteger id, String name) {
        this.id = id;
        this.name = name;
    }
}
