package ru.otus.util.reader;

public class IncorrectAswerFormatException extends CsvReaderException {
    protected IncorrectAswerFormatException(String message) {
        super(message);
    }
}
