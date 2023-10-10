package ru.otus.gateway;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.domain.ApprovedLesson;
import ru.otus.domain.LessonRequest;

import java.util.Collection;

@MessagingGateway
public interface OnlineSchoolGateway {

    @Gateway(requestChannel = "inputChannel", replyChannel = "outputChannel")
    Collection<ApprovedLesson> process(Collection<LessonRequest> lessonRequests);
}
