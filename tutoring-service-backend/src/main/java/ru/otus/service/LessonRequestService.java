package ru.otus.service;

import ru.otus.domain.LessonRequest;

import java.util.List;
import java.util.UUID;

public interface LessonRequestService {

    List<LessonRequest> findAll();

    void createRequest(LessonRequest lessonRequest);

    boolean updateRequest(UUID id, LessonRequest lessonRequest);

}
