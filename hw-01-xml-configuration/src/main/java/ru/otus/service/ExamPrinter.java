package ru.otus.service;

import ru.otus.domain.Task;

import java.util.List;

public interface ExamPrinter {
    void printAll(List<Task> tasks);
}
