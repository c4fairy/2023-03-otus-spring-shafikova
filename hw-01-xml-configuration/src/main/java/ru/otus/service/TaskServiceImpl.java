package ru.otus.service;

import ru.otus.dao.TaskDao;
import ru.otus.domain.Task;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class TaskServiceImpl implements TaskService {
    private final TaskDao dao;

    public TaskServiceImpl(TaskDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Task> getAllTasks() throws IOException, URISyntaxException {
        return dao.getAllTasks();
    }
}
