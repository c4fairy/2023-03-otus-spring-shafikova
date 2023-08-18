package ru.otus.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Question {

    private final String text;

    private final List<Answer> answers;

    public Question(final String text) {
        this.text = text;
        answers = new ArrayList();
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

}
