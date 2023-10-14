package ru.otus.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "subject")
public class Subject {
    @Id
    private UUID id;

    @Column(name = "name", unique = true)
    private String name;

    public Subject(String name) {
        this.name = name;
    }
}
