package ru.otus.util.printer;

import lombok.SneakyThrows;
import org.springframework.context.MessageSource;
import ru.otus.config.ApplicationConfig;
import ru.otus.domain.Answer;
import ru.otus.domain.Exam;
import ru.otus.domain.Line;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import java.util.Optional;

public class ExamPrinterImpl implements ExamPrinter {

    private final Exam exam;

    private final int passScore;

    private final BufferedReader in;

    private final PrintStream out;

    private final MessageSource messageSource;

    private final ApplicationConfig props = new ApplicationConfig();

    private int correctAnswerCounter;

    private boolean examResult;

    private boolean isExamFinished;

    public ExamPrinterImpl(Exam exam, BufferedReader in, PrintStream out, MessageSource messageSource) {
        this.exam = exam;
        this.in = in;
        this.out = out;
        this.messageSource = messageSource;
        final int passScore = exam.getPassScore();
        checkScoreValue(passScore);
        this.passScore = passScore;
    }

    @SneakyThrows
    @Override
    /**
     * Prints out data from Exam object
     * @param exam Exam object for print
     */
    public void print() {
        out.println(getMessageFromProps("msg.start.welcome"));
        out.println(getMessageFromProps("msg.start.firstName") + ":");
        String firstName = readLine();
        out.println(getMessageFromProps("msg.start.lastName") + ":");
        String lastName = readLine();
        out.println(getMessageFromProps("msg.start.greeting") + ", " + firstName + " " + lastName);
        exam.getLines().forEach(this::accept);
        final int userResult = getPercentageOfCompletion();
        isExamFinished = true;
        setExamResult(userResult);
        printExamResult(firstName, lastName, userResult);
    }

    private String readLine() throws IOException {
        String line;
        StringBuilder rslt = new StringBuilder();
        if ((line = in.readLine()) != null) {
            rslt.append(line);
        }
        return rslt.toString();
    }

    private void printExamResult(String firstName, String lastName, int userResult) {
        out.println(firstName.toUpperCase() + " " + lastName.toUpperCase() + ", " + getMessageFromProps("msg.finish.result") + " : " + userResult + "%");
        out.println(getMessageFromProps("msg.finish.result.score") + ": ".toUpperCase() + passScore + "%");
        if (getExamResult()) {
            out.println(getMessageFromProps("msg.finish.result.passed"));
        } else {
            out.println(getMessageFromProps("msg.finish.result.failed") + "...");
        }
    }

    @Override
    public boolean getExamResult() {
        if (!isExamFinished) {
            throw new NotFinishedExamException("Not possible to show result of exam. Exam is not finished");
        } else {
            return examResult;
        }
    }

    private void setExamResult(int userResult) {
        examResult = userResult >= passScore;
    }

    private int getPercentageOfCompletion() {
        return correctAnswerCounter * 100 / exam.getLines().size();
    }

    @SneakyThrows
    private void proceedUserAnswerLetter(Character correctAnswerLetter) {
        out.println("Enter the correct answer letter A B C or D ...");
        String userAnswer = readLine();
        if (userAnswer.length() > 0) {
            out.print(getMessageFromProps("msg.inprogress.answer.text") + " ");
            if (correctAnswerLetter.equals(userAnswer.toUpperCase().charAt(0))) {
                out.print(getMessageFromProps("msg.inprogress.answer.positiveresult"));
                correctAnswerCounter++;
            } else {
                out.print(getMessageFromProps("msg.inprogress.answer.negativeresult"));
            }
            out.println();
        } else {
            out.println(getMessageFromProps("msg.inprogress.answer.empty") + "...");
        }
    }

    private void accept(Line l) {
        out.println(l.getQuestion());
        final List<Answer> answers = l.getAnswers();
        answers.forEach(answer -> {
            out.println(answer.getText());
        });
        final Optional<Answer> first = answers.stream().filter(Answer::isCorrectAnswer).findFirst();
        if (first.isEmpty()) {
            throw new RuntimeException("There is no correct answers in csv file. " +
                    "Correct answer should be mark with '+' at the end");
        }
        proceedUserAnswerLetter(first.get().getAnswerOptionLetter());
    }

    private void checkScoreValue(Integer passScore) {
        if (!((passScore >= 0) && (passScore <= 100))) {
            throw new IncorrectScoreValueException("Incorrect score value. Value should be: 0 >= value <= 100");
        }
    }

    private String getMessageFromProps(String s) {
        return messageSource.getMessage(s, null, props.getLocale()).toUpperCase();
    }

}
