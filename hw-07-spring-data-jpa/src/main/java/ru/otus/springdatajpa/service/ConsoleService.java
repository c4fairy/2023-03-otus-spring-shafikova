package ru.otus.springdatajpa.service;

public interface ConsoleService {
    void write(String text);

    String read();

    Integer readInt();

    void write(long count);

}
