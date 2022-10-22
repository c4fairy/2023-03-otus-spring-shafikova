package ru.otus.service;

import ru.otus.domain.Task;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface TaskService {
    List<Task> getAllTasks() throws IOException, URISyntaxException;
}
