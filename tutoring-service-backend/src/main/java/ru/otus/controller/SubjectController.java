package ru.otus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.domain.Subject;
import ru.otus.service.SubjectService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/subjects")
@RequiredArgsConstructor
public class SubjectController {
    private final SubjectService subjectService;

    @GetMapping("/")
    public ResponseEntity<?> getAllSubjects() {
        List<Subject> subjects = subjectService.findAll();
        return subjects != null && !subjects.isEmpty()
                ? new ResponseEntity<>(subjects, HttpStatus.OK)
                : new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }
}
