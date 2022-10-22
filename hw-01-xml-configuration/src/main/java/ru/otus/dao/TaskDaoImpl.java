package ru.otus.dao;

import ru.otus.domain.Task;
import ru.otus.util.CsvReader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class TaskDaoImpl implements TaskDao {
    String fileName;
    String delimeter;

    public TaskDaoImpl(String fileName, String delimiter) {
        this.fileName = fileName;
        this.delimeter = delimiter;
    }

    @Override
    public List<Task> getAllTasks() throws IOException, URISyntaxException {
        return CsvReader.getAllTasks(fileName, delimeter);
    }
}
