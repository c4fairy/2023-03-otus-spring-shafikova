package ru.otus.service.impl;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.otus.config.ApplicationConfig;
import ru.otus.domain.Answer;
import ru.otus.domain.Exam;
import ru.otus.domain.Question;
import ru.otus.service.CsvReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CsvReaderImpl implements CsvReader {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private final ApplicationConfig config;

    public CsvReaderImpl(ApplicationConfig config) {
        this.config = config;
    }

    @Override
    public Exam getExam() {
        List<List<String>> records = readAll();
        return recordsToExam(records);
    }

    private List<List<String>> readAll() {
        setCSVLocalizedResourceName();
        try (InputStream is = CsvReader.class.getResourceAsStream("/" + config.getFileName())) {
            if (is != null) {
                try (CSVReader csvReader = new CSVReader(new InputStreamReader(is));) {
                    String[] values;
                    List<List<String>> records = new ArrayList();
                    while ((values = csvReader.readNext()) != null) {
                        records.add(Arrays.asList(values));
                    }
                    return records;
                } catch (IOException e) {
                    LOGGER.error(e.getMessage(), e);
                } catch (CsvValidationException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    private void setCSVLocalizedResourceName() {
        if (config.getLocale() != null) {
            String csvLocaleFileName = "i18n/" + config.getFileName().substring(0, config.getFileName().indexOf('.')) + "_" + config.getLocale() + "." + config.getFileName().substring(config.getFileName().indexOf('.') + 1);
            try (InputStream is = CsvReader.class.getResourceAsStream("/" + csvLocaleFileName)) {
                if (is != null) {
                    config.setFileName(csvLocaleFileName);
                } else {
                    LOGGER.info("Resource " + config.getFileName() + " with locale " + config.getLocale() + " not found");
                }
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
                config.setFileName("i18n/" + config.getFileName().substring(0, config.getFileName().indexOf('.')) + "_ru.csv");
            }
        }
    }

    private Exam recordsToExam(List<List<String>> records) {
        if (records != null && !records.isEmpty()) {
            Exam sv = new Exam();
            for (List<String> record : records) {
                if (!record.isEmpty()) {
                    Question q = new Question(record.get(0));
                    for (int i = 1; i < record.size(); i++) {
                        String text = record.get(i).endsWith("*") ?
                                record.get(i).substring(0, record.get(i).length() - 1) : record.get(i);
                        boolean isRight = record.get(i).endsWith("*");
                        Answer a = new Answer(text, isRight);
                        q.addAnswer(a);
                    }
                    sv.addQuestion(q);
                }
            }
            return sv;
        }
        return null;
    }
}
