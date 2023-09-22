package ru.otus.service.impl;

import lombok.SneakyThrows;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.config.ApplicationConfig;
import ru.otus.domain.Answer;
import ru.otus.domain.Exam;
import ru.otus.domain.Question;
import ru.otus.service.CsvReader;
import ru.otus.service.ExamService;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

@Service
public class ExamServiceImpl implements ExamService {
    private final CsvReader reader;

    private final MessageSource messageSource;

    private final Locale locale;

    private final ApplicationConfig config;

    public ExamServiceImpl(CsvReader reader, MessageSource messageSource, ApplicationConfig config) {
        this.reader = reader;
        this.locale = config.getLocale() == null ? new Locale("ru") : new Locale(config.getLocale());
        this.config = config;
        this.messageSource = messageSource;
    }

    @SneakyThrows
    @Override
    public void startExam() {
        Exam exam = reader.getExam();
        if (exam != null && exam.getQuestions() != null && !exam.getQuestions().isEmpty()) {
            PrintStream out = new PrintStream(System.out);
            Scanner in = new Scanner(System.in);
            out.println(getMessageFromConfig("msg.start.welcome"));
            out.println(getMessageFromConfig("msg.start.firstName") + ":");
            String firstName = in.nextLine();
            out.println(getMessageFromConfig("msg.start.lastName") + ":");
            String lastName = in.nextLine();
            out.println(getMessageFromConfig("msg.start.greeting") + ", " + firstName + " " + lastName);
            int userResult = 0;
            int qCount = 0;
            for (Question question : exam.getQuestions()) {
                if (question.getAnswers().size() > 1) {
                    qCount++;
                    int tryCount = 3;
                    System.out.println(question.getText().trim());
                    for (Answer a : question.getAnswers()) {
                        System.out.println("  " + a.getText().trim());
                    }
                    int number = getNumber(in, question.getAnswers().size(), tryCount);
                    if (number > 0 && question.getAnswers().get(number - 1).isRigth()) {
                        userResult++;
                    }
                }
            }
            printExamResult(firstName, lastName, userResult);
        }
    }

    private int getNumber(Scanner in, int answerCount, int tryCount) {
        if (tryCount < 0) {
            return -1;
        }
        System.out.println(messageSource.getMessage("msg.inprogress.answer.text", new Object[]{answerCount}, locale));
        int number = 1;
        try {
            number = in.nextInt();
        } catch (InputMismatchException e) {
            number = 0;
        }
        return number;
    }

    private void printExamResult(String firstName, String lastName, int userResult) {
        int passScore = config.getPassScore();
        System.out.println(firstName.toUpperCase() + " " + lastName.toUpperCase() + ", " + getMessageFromConfig("msg.finish.result") + " : " + userResult);
        System.out.println(getMessageFromConfig("msg.finish.result.score") + ": ".toUpperCase() + passScore);
        if (userResult >= passScore) {
            System.out.println(getMessageFromConfig("msg.finish.result.passed"));
        } else {
            System.out.println(getMessageFromConfig("msg.finish.result.failed") + "...");
        }
    }

    private String getMessageFromConfig(String s) {
        return messageSource.getMessage(s, null, locale);
    }
}
