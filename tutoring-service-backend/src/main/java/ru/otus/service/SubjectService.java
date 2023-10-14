package ru.otus.service;

import ru.otus.domain.Subject;

import java.util.List;

public interface SubjectService {
    Subject findByName(String subjectName);

    Subject createSubject(Subject subject);
    List<Subject> findAll();
}
