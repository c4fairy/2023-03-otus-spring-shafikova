package ru.otus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.domain.LessonRequest;
import ru.otus.service.LessonRequestService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/lesson_request")
public class LessonRequestController {
    private final LessonRequestService lessonRequestService;

    @GetMapping("/")
    public ResponseEntity<List<LessonRequest>> getAllLessonRequests() {
        List<LessonRequest> lessonRequests = lessonRequestService.findAll();
        return lessonRequests != null && !lessonRequests.isEmpty()
                ? new ResponseEntity<>(lessonRequests, HttpStatus.OK)
                : new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createLessonRequest(@RequestBody LessonRequest lessonRequest) {
        lessonRequestService.createRequest(lessonRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateLessonRequest(@PathVariable("id") UUID id, @RequestBody LessonRequest lessonRequest) {
        boolean updated = lessonRequestService.updateRequest(id, lessonRequest);
        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
