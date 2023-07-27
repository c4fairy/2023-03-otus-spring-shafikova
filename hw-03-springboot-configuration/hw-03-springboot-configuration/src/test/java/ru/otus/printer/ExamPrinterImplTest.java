package ru.otus.printer;

import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import ru.otus.config.ApplicationConfig;
import ru.otus.domain.Answer;
import ru.otus.domain.Exam;
import ru.otus.domain.Line;
import ru.otus.util.printer.ExamPrinter;
import ru.otus.util.printer.ExamPrinterImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ExamPrinterImplTest {

    @Autowired
    PrintStream writer;
    @Autowired
    ApplicationConfig props;
    @Autowired
    MessageSource messageSource;
    @Mock
    BufferedReader bufferedReader;
    private ExamPrinter examPrinterImpl;
    @Mock
    private Exam exam;


    @SneakyThrows
    @Test
    @Order(1)
    void shouldPassZeroScore() {
        fullExam("Z", "Z", "Z", 0);
        assertTrue(examPrinterImpl.getExamResult());
    }

    @Test
    @Order(2)
    void shouldFailedForLessPossiblePassScore() throws IOException {
        fullExam("D", "B", "Z", 100);
        assertFalse(examPrinterImpl.getExamResult());
    }

    @Test
    @Order(3)
    void shouldPassEqualsPassScore() throws IOException {
        fullExam("D", "B", "Z", 66);
        assertTrue(examPrinterImpl.getExamResult());
    }

    @Test
    @Order(4)
    void shouldFailedForLessThanPassScore() throws IOException {
        fullExam("D", "Z", "Z", 66);
        assertFalse(examPrinterImpl.getExamResult());
    }

    void fullExam(String firstAnswerFromUser, String secondAnswerFromUser, String thirdAnswerFromUser, int passScore) throws IOException {
        when(exam.getPassScore()).thenReturn(passScore);
        when(bufferedReader.readLine())
                .thenReturn("First name")
                .thenReturn("Last name")
                .thenReturn(firstAnswerFromUser)
                .thenReturn(secondAnswerFromUser)
                .thenReturn(thirdAnswerFromUser);
        char firstCorrectLetter = 'D';
        char secondCorrectLetter = 'B';
        char thirdCorrectLetter = 'A';
        final List<Line> lines = Arrays.asList(
                new Line("q1", Collections.singletonList(new Answer(firstCorrectLetter, "", true))),
                new Line("q2", Collections.singletonList(new Answer(secondCorrectLetter, "", true))),
                new Line("q3", Collections.singletonList(new Answer(thirdCorrectLetter, "", true)))
        );
        when(exam.getLines()).thenReturn(lines);
        examPrinterImpl = new ExamPrinterImpl(exam, bufferedReader, writer, messageSource);
        examPrinterImpl.print();
    }

}