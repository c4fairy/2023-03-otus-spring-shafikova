package ru.otus.dao;

import ru.otus.domain.Task;
import ru.otus.util.CsvReader;
import ru.otus.util.TaskUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class TaskDaoImpl implements TaskDao {

    private final CsvReader csvReader;

    private final TaskUtil taskUtil;

    private final String fileName;

    private final String delimeter;

    public TaskDaoImpl(CsvReader csvReader, TaskUtil taskUtil, String fileName, String delimiter) {
        this.csvReader = csvReader;
        this.taskUtil = taskUtil;
        this.fileName = fileName;
        this.delimeter = delimiter;
    }

    @Override
    public List<Task> getAllTasks() throws IOException, URISyntaxException {
        return taskUtil.getTasksFromStrings(csvReader.getAllTasks(fileName), delimeter);
    }
}
