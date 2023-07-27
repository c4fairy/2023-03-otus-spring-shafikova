package ru.otus.reader;

import com.opencsv.CSVReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.domain.Exam;
import ru.otus.util.reader.CsvReader;
import ru.otus.util.reader.CsvReaderImpl;
import ru.otus.util.reader.IncorrectAswerFormatException;
import ru.otus.util.reader.NoAnwersException;
import ru.otus.util.reader.NoCorrectAnswerException;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
class CsvReaderImplTest {

    private final String question = "q?";
    private final String answer1 = "A - ";
    private final String answer2 = "B - ";
    private final String answer3 = "C - ";
    private final String answer4 = "D - +";
    private final String[] csvLine = {
            question, answer1, answer2, answer3, answer4
    };
    private final int defaultPassScore = 80;

    @Mock
    private CSVReader csvReader;
    @Mock
    private FileReader fileReader;

    private CsvReader csvReaderImpl;

    @BeforeEach
    void beforeEach() {
        csvReaderImpl = new CsvReaderImpl(csvReader, fileReader);
    }

    @Test
    @Order(1)
    void shouldApplyScoreValue() throws Exception {
        when(csvReader.readAll()).thenReturn(new ArrayList<>());
        assertEquals(csvReaderImpl.getAsExam(defaultPassScore).getPassScore(), defaultPassScore);
    }

    @Test
    @Order(2)
    void shouldCheckExamSize() throws Exception {
        when(csvReader.readAll()).thenReturn(Arrays.asList(csvLine, csvLine));
        final Exam exam = csvReaderImpl.getAsExam(defaultPassScore);
        assertEquals(exam.getLines().size(), 2);
    }

    @Test
    @Order(3)
    void shouldThrowsExceptionForNoAnswer() throws Exception {
        when(csvReader.readAll()).thenReturn(Collections.singletonList(new String[]{"Winter"}));
        assertThrows(NoAnwersException.class, () -> csvReaderImpl.getAsExam(defaultPassScore));
    }

    @Test
    @Order(4)
    void shouldThrowsExceptionForIncorrectFormatAnswer() throws Exception {
        when(csvReader.readAll()).thenReturn(Collections.singletonList(new String[]{"Winter", "Spring+", "Summer", "Autumn"}));
        assertThrows(IncorrectAswerFormatException.class, () -> csvReaderImpl.getAsExam(defaultPassScore));
    }

    @Test
    @Order(5)
    void shouldThrowsExceptionForIncorrectAnswer() throws Exception {
        when(csvReader.readAll()).thenReturn(Collections.singletonList(
                new String[]{question, answer1, answer2, answer3, answer4.replace("+", "")}));
        assertThrows(NoCorrectAnswerException.class, () -> csvReaderImpl.getAsExam(defaultPassScore));
    }
}