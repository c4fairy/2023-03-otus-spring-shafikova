package ru.otus.service;

import ru.otus.dao.TaskDao;
import ru.otus.domain.Task;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class TaskServiceImpl implements TaskService {
    private final TaskDao dao;

    private final ExamPrinter printer;

    public TaskServiceImpl(TaskDao dao, ExamPrinter printer) {
        this.dao = dao;
        this.printer = printer;
    }

    @Override
    public List<Task> getAllTasks() throws IOException, URISyntaxException {
        var tasks = dao.getAllTasks();
        printer.printAll(tasks);
        return tasks;
    }
}
