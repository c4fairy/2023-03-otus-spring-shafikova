package ru.otus.util.reader;


import ru.otus.domain.Exam;

public interface CsvReader {
    Exam getAsExam(int passScore) throws Exception;
}
