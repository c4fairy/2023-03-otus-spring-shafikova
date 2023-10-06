package ru.otus.domain;

import java.util.Date;

public record LessonRequest(String lessonName, Date lessonDate, String studentName, String tutorName) {

}
