package ru.otus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import ru.otus.config.ApplicationConfig;
import ru.otus.util.printer.ExamPrinter;


@SpringBootApplication
@EnableConfigurationProperties(ApplicationConfig.class)
public class ExamApp {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ExamApp.class, args);
		final ExamPrinter examPrinter = context.getBean(ExamPrinter.class);
		examPrinter.print();
	}
}