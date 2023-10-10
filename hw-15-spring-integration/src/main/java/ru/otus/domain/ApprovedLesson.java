package ru.otus.domain;


import java.util.Date;

public record ApprovedLesson(String approvedLessonName, Date lessonDate, String studentName, String tutorName) {

}
