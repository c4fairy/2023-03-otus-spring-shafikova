package ru.otus.dao;

import ru.otus.domain.Task;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface TaskDao {
    List<Task> getAllTasks() throws IOException, URISyntaxException;
}
