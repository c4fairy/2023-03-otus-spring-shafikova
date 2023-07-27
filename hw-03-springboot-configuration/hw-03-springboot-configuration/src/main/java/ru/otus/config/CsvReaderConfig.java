package ru.otus.config;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import ru.otus.domain.Exam;
import ru.otus.util.reader.CsvReader;
import ru.otus.util.reader.CsvReaderImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

@Configuration
public class CsvReaderConfig {
    private final File file;

    private final Integer passScore;

    public CsvReaderConfig(@Value("${application.fileName}_${application.locale}.csv") String fileName,
                           @Value("${application.passScore}") Integer passScore) throws FileNotFoundException {

        file = ResourceUtils.getFile("classpath:" + fileName);
        checkScoreValue(passScore);
        this.passScore = passScore;
    }

    public CsvReader csvReader() throws FileNotFoundException {
        FileReader fileReader = new FileReader(file);
        CSVReader csvReader = new CSVReaderBuilder(fileReader).withSkipLines(1).build();
        return new CsvReaderImpl(csvReader, fileReader);
    }

    @Bean
    public Exam exam() throws Exception {
        return csvReader().getAsExam(passScore);
    }

    private void checkScoreValue(Integer passScore) {
        if (!((passScore >= 0) && (passScore <= 100))) {
            throw new RuntimeException("Incorrect score value. Value should be: 0 >= value <= 100");
        }
    }
}
