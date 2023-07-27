package ru.otus.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import ru.otus.domain.Exam;
import ru.otus.util.printer.ExamPrinter;
import ru.otus.util.printer.ExamPrinterImpl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;

@Configuration
public class ExamPrinterConfig {

    private final Exam exam;

    public ExamPrinterConfig(Exam exam) {
        this.exam = exam;
    }

    @Bean
    public ExamPrinter examPrinter(BufferedReader bufferedReader, PrintStream out, MessageSource messageSource) throws Exception {
        return new ExamPrinterImpl(exam, bufferedReader, out, messageSource);
    }

    @Bean
    public PrintStream out() {
        return System.out;
    }

    @Bean
    public BufferedReader bufferedReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    @Bean
    public MessageSource messageSource() {
        var ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename("classpath:/i18n/bundle");
        ms.setDefaultEncoding("UTF-8");
        return ms;
    }

}
