package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.domain.LessonRequest;
import ru.otus.domain.Person;
import ru.otus.domain.Subject;
import ru.otus.repository.LessonRequestRepository;
import ru.otus.service.LessonRequestService;
import ru.otus.service.PersonService;
import ru.otus.service.SubjectService;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static ru.otus.enums.LessonRequestStatus.CREATED;

@Service
@RequiredArgsConstructor
public class LessonRequestServiceImpl implements LessonRequestService {
    private final LessonRequestRepository lessonRequestRepository;
    private final PersonService personService;
    private final SubjectService subjectService;

    @Override
    public List<LessonRequest> findAll() {
        return lessonRequestRepository.findAll();
    }

    @Override
    public void createRequest(LessonRequest lessonRequest) {
        Person tutor = personService.findByFio(lessonRequest.getTutor().getFio()).orElseThrow();
        Person student = personService.findByFio(lessonRequest.getStudent().getFio()).orElseThrow();
        Subject subject = subjectService.findByName(lessonRequest.getSubject().getName());
        if (subject == null) subject = new Subject(lessonRequest.getSubject().getName());
        lessonRequest.setSubject(subject);
        lessonRequest.setStudent(student);
        lessonRequest.setStudent(tutor);
        lessonRequest.setPaid(false);
        lessonRequest.setLessonStart(OffsetDateTime.now());
        lessonRequest.setLessonEnd(OffsetDateTime.now());
        lessonRequest.setStatus(CREATED.getName());
        lessonRequestRepository.save(lessonRequest);
    }

    @Override
    public boolean updateRequest(UUID id, LessonRequest lessonRequest) {
        update(id, lessonRequest);
        return true;
    }


    private void update(UUID id, LessonRequest lessonRequest) {
        LessonRequest updatingLessonRequest = lessonRequestRepository.findById(id).orElseThrow();
        updatingLessonRequest.setStatus(lessonRequest.getStatus());
        updatingLessonRequest.setSubject(lessonRequest.getSubject());
        updatingLessonRequest.setStudent(lessonRequest.getStudent());
        updatingLessonRequest.setTutor(lessonRequest.getTutor());
        updatingLessonRequest.setLessonStart(lessonRequest.getLessonStart());
        updatingLessonRequest.setLessonEnd(lessonRequest.getLessonEnd());
        lessonRequestRepository.save(updatingLessonRequest);
    }

}
