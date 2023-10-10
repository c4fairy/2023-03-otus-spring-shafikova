package ru.otus.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.domain.ApprovedLesson;
import ru.otus.domain.LessonRequest;
import ru.otus.service.LessonService;

@Service
@Slf4j
public class LessonServiceImpl implements LessonService {

    @Override
    public ApprovedLesson approve(LessonRequest lessonRequest) {
        log.info("Requested lessons {}", lessonRequest.lessonName(), lessonRequest.lessonDate(), lessonRequest.studentName(), lessonRequest.tutorName());
        delay();
        log.info("Approved lessons {}", lessonRequest.lessonName());
        return new ApprovedLesson(lessonRequest.lessonName(), lessonRequest.lessonDate(), lessonRequest.studentName(), lessonRequest.tutorName());
    }

    private static void delay() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
