package ru.otus;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.config.ApplicationConfig;
import ru.otus.domain.Exam;
import ru.otus.domain.Question;
import ru.otus.service.CsvReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SpringBootAppTest {
	@Autowired
	private ApplicationConfig config;

	@Autowired
	private CsvReader reader;

	@Test
	public void contextLoads() throws Exception {
		Exam exam = reader.getExam();
		assertEquals(5,exam.getQuestions().size());
		for (Question question: exam.getQuestions()) {
			assertEquals(4, question.getAnswers().size());
		}
	}

}