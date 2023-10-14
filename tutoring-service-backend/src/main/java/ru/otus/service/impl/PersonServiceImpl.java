package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.domain.Person;
import ru.otus.repository.PersonRepository;
import ru.otus.service.PersonService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public void createPerson(Person person) {
        personRepository.save(person);
    }

    @Override
    public boolean updatePerson(UUID id, Person person) {
        update(id, person);
        return true;
    }

    @Override
    public Optional<Person> findByFio(String fio) {
        return personRepository.findByFio(fio);
    }

    private void update(UUID id, Person person) {
        Person updatingPerson = personRepository.findById(id).orElseThrow();
        updatingPerson.setFio(person.getFio());
        updatingPerson.setEmail(person.getEmail());
        updatingPerson.setPhone(person.getPhone());
        updatingPerson.setTutorFlag(person.getTutorFlag());
        personRepository.save(updatingPerson);
    }
}
