package ru.otus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.domain.LessonRequest;

import java.util.List;
import java.util.UUID;

public interface LessonRequestRepository extends JpaRepository<LessonRequest, UUID> {

    List<LessonRequest> findAll();

}
