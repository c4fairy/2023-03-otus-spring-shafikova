package ru.otus.domain;

import lombok.Data;

import java.util.List;

@Data
public class Task {

    private String question;
    private String answer;
    private List<String> options;

    public Task(String question, String answer, List<String> options) {
        this.question = question;
        this.answer = answer;
        this.options = options;
    }

    @Override
    public String toString() {
        return "Questionier [question=" + question + ", options=" + options + ", answer=" + answer + "]";
    }
}
