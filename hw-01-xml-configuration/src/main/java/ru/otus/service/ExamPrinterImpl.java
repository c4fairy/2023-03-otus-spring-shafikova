package ru.otus.service;

import ru.otus.domain.Task;

import java.io.PrintStream;
import java.util.List;

public class ExamPrinterImpl implements ExamPrinter {

    private static final PrintStream printStream = new PrintStream(System.out);

    public void printAll(List<Task> tasks) {
        tasks.forEach(printStream::println);
    }
}
