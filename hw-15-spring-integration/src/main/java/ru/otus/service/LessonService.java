package ru.otus.service;

import ru.otus.domain.ApprovedLesson;
import ru.otus.domain.LessonRequest;

public interface LessonService {

    ApprovedLesson approve(LessonRequest orderItem);
}
