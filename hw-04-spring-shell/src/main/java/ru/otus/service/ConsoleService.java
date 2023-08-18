package ru.otus.service;


import ru.otus.domain.Question;

public interface ConsoleService {

    public static final String DEFAULT_LOCALE = "ru";

    public static final String NAME = "msg.start.name";

    public static final String FINISH_RESULT = "msg.finish.result";

    public static final String RESULT_PASSED = "msg.finish.result.passed";

    public static final String RESULT_FAILED = "msg.finish.result.failed";

    public static final String ANSWER_TEXT = "msg.inprogress.answer.text";

    String getName();

    void printQuestion(Question question);

    public void printAnswer(int answerCount);

    int getAnswer();

    void printResult(String name, int userResult, int passScore);

    void printError(String errorName);
}
