package ru.otus.enums;

public enum LessonRequestStatus {
    CREATED ("created");

    private String name;

    LessonRequestStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
