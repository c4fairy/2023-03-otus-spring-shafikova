package ru.otus.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CsvReader {
    public List<String> getAllTasks(String fileName) throws IOException, URISyntaxException {
        BufferedReader reader = new BufferedReader(
                Files.newBufferedReader(
                        Path.of(Objects.requireNonNull(
                                CsvReader.class.getClassLoader().getResource(fileName)).toURI()
                        )
                )
        );

        List<String> strings = new ArrayList<>();

        try {
            while (reader.ready()) {
                strings.add(reader.readLine());
            }
        } catch (IOException e) {
            throw new IOException();
        }

        return strings;
    }
}
