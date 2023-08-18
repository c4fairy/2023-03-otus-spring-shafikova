package ru.otus.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.config.ApplicationConfig;
import ru.otus.domain.Exam;
import ru.otus.domain.Question;
import ru.otus.service.ConsoleService;
import ru.otus.service.CsvReader;
import ru.otus.service.ExamService;

@Service
public class ExamServiceImpl implements ExamService {
    private final CsvReader reader;

    private final ApplicationConfig config;

    private final ConsoleService consoleService;

    private String name;

    private int userResult = 0;

    public ExamServiceImpl(CsvReader reader, ApplicationConfig config, ConsoleService consoleService) {
        this.reader = reader;
        this.config = config;
        this.consoleService = consoleService;
    }

    @Override
    public void getGreetingName() {
        name = consoleService.getName();
    }

    @Override
    public void startExam() throws Exception {
        Exam exam = reader.getExam();
        if (exam != null && exam.getQuestions() != null && !exam.getQuestions().isEmpty()) {
            int qCount = 0;
            for (Question question : exam.getQuestions()) {
                if (question.getAnswers().size() > 1) {
                    qCount++;
                    int tryCount = 3;
                    consoleService.printQuestion(question);
                    int number = getNumber(question.getAnswers().size(), tryCount);
                    if (number > 0 && question.getAnswers().get(number - 1).isRigth()) {
                        userResult++;
                    }
                }
            }
        }
    }

    private int getNumber(int answerCount, int tryCount) {
        if (tryCount < 0) {
            return -1;
        }
        consoleService.printAnswer(answerCount);
        int number = consoleService.getAnswer();
        if (number < 1 || number > answerCount) {
            return getNumber(answerCount, tryCount - 1);
        }
        return number;
    }

    @Override
    public void printExamResult() {
        int passScore = config.getPassScore();
        consoleService.printResult(name, userResult, passScore);
    }
}
