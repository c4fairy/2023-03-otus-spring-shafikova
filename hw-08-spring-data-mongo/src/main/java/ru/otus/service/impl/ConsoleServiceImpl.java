package ru.otus.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.service.ConsoleService;

import java.util.Scanner;

@Service
@Slf4j
public class ConsoleServiceImpl implements ConsoleService {
    private final Scanner sc = new Scanner(System.in);

    @Override
    public void write(String text) {
        log.info(text);
    }

    @Override
    public String read() {
        return sc.nextLine();
    }

    @Override
    public Integer readInt() {
        int count = sc.nextInt();
        sc.nextLine();
        return count;
    }

    @Override
    public void write(long count) {
        log.info(String.valueOf(count));
    }

}