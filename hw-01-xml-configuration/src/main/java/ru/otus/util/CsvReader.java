package ru.otus.util;

import ru.otus.domain.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CsvReader {
    public static List<Task> getAllTasks(String fileName, String delimiter) throws IOException, URISyntaxException {
        BufferedReader reader = new BufferedReader(Files.newBufferedReader(Path.of(CsvReader.class.getClassLoader().getResource(fileName).toURI())));

        List<String> strings = new ArrayList<>();

        try {
            while (reader.ready()) {
                strings.add(reader.readLine());
            }
        } catch (IOException e) {
            throw new IOException();
        }

        return strings.stream().map(x -> {
            String[] split = x.split(delimiter, 3);
            return new Task(split[0], split[1], Arrays.asList(split[2].split(delimiter)));
        }).collect(Collectors.toList());

    }
}
