package ru.otus.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "person_info")
public class Person {
    @Id
    private UUID id;

    private String fio;

    private String phone;

    private String email;

    @Column(name = "tutor")
    private Boolean tutorFlag;

    private BigDecimal balance;


    public Person(String fio) {
        this.fio = fio;
    }


}
