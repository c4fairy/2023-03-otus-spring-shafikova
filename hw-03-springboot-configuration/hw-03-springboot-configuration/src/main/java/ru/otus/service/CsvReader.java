package ru.otus.service;


import ru.otus.domain.Exam;

public interface CsvReader {
    Exam getExam() throws Exception;
}
