package ru.otus.service.impl;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.config.ApplicationConfig;
import ru.otus.domain.Answer;
import ru.otus.domain.Question;
import ru.otus.service.ConsoleService;
import ru.otus.service.CsvReader;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

@Service
public class ConsoleServiceImpl implements ConsoleService {

    private final MessageSource ms;

    private final Locale locale;

    private final Scanner in = new Scanner(System.in);


    public ConsoleServiceImpl(final CsvReader reader, final MessageSource ms, final ApplicationConfig config) {
        this.ms = ms;
        this.locale = config.getLocale() == null ? new Locale(ConsoleService.DEFAULT_LOCALE) : new Locale(config.getLocale());
    }

    @Override
    public String getName() {
        System.out.println(ms.getMessage("msg.start.welcome", null, locale));
        System.out.println(ms.getMessage(ConsoleService.NAME, null, locale));
        return in.nextLine();
    }

    @Override
    public void printQuestion(Question q) {
        System.out.println(q.getText().trim());
        for (Answer a : q.getAnswers()) {
            System.out.println("  " + a.getText().trim());
        }
    }

    @Override
    public void printResult(String name, int userResult, int passScore) {
        System.out.println(ms.getMessage(ConsoleService.FINISH_RESULT, new Object[]{name, userResult}, locale));
        if (userResult >= passScore) {
            System.out.println(ms.getMessage(ConsoleService.RESULT_PASSED, null, locale));
        } else {
            System.out.println(ms.getMessage(ConsoleService.RESULT_FAILED, null, locale));
        }
    }

    @Override
    public void printAnswer(int answerCount) {
        System.out.println(ms.getMessage(ConsoleService.ANSWER_TEXT, new Object[]{answerCount}, locale));
    }

    @Override
    public int getAnswer() {
        int number;
        try {
            number = in.nextInt();
        } catch (InputMismatchException e) {
            number = 0;
        }
        return number;
    }

    public void printError(String errorName) {
        System.out.println(ms.getMessage(errorName, null, locale));
    }
}