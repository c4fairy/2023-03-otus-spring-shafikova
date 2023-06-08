package ru.otus.util;

import ru.otus.domain.Task;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TaskUtil {

    public List<Task> getTasksFromStrings(List<String> strings, String delimiter){
        return strings.stream().map(x -> {
            String[] split = x.split(delimiter, 3);
            return new Task(split[0], split[1], Arrays.asList(split[2].split(delimiter)));
        }).collect(Collectors.toList());
    }
}
