package ru.otus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.domain.Person;
import ru.otus.service.PersonService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/person")
@RequiredArgsConstructor
public class
PersonController {
    private final PersonService personService;

    @PostMapping("/")
    public ResponseEntity<?> createPerson(@RequestBody Person person) {
        personService.createPerson(person);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Person>> getAllPersons() {
        List<Person> persons = personService.findAll();
        return persons != null && !persons.isEmpty()
                ? new ResponseEntity<>(persons, HttpStatus.OK)
                : new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") UUID id, @RequestBody Person person) {
        boolean updated = personService.updatePerson(id, person);
        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
