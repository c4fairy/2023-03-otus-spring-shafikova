package ru.otus.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "comment")
public class Comment {
    @Transient
    public static final String SEQUENCE_NAME = "comments_sequence";

    @Id
    private BigInteger id;
    private String text;

    public Comment(String text) {
        this.text = text;
    }
}
