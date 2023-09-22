package ru.otus.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Exam {
    private final List<Question> questions = new ArrayList();

    public void addQuestion(Question q) {
        questions.add(q);
    }
}
