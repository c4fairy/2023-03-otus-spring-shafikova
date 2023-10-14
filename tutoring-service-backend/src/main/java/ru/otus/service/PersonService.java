package ru.otus.service;


import ru.otus.domain.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonService {
    List<Person> findAll();

    void createPerson(Person person);

    boolean updatePerson(UUID id, Person person);

    Optional<Person> findByFio(String fio);
}
