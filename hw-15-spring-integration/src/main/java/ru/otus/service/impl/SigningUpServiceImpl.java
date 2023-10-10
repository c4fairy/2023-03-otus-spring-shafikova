package ru.otus.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;
import ru.otus.domain.ApprovedLesson;
import ru.otus.domain.LessonRequest;
import ru.otus.gateway.OnlineSchoolGateway;
import ru.otus.service.SigningUpService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static java.lang.System.currentTimeMillis;

@Service
@Slf4j
public class SigningUpServiceImpl implements SigningUpService {
	private static final String[] LESSONS = {"math", "programming", "english"};
	private static final String[] STUDENTS = {"student1", "student2", "student3", "student4", "student5"};
	private static final String[] TUTORS = {"tutor1", "tutor2", "tutor4"};

	private final OnlineSchoolGateway school;

	public SigningUpServiceImpl(OnlineSchoolGateway school) {
		this.school = school;
	}

	@Override
	public void startGenerateLessonsLoop() {
		ForkJoinPool pool = ForkJoinPool.commonPool();
		for (int i = 0; i < 10; i++) {
			delay();
			int num = i + 1;
			pool.execute(() -> {
				Collection<LessonRequest> lessonRequests = generateLessonRequests();
				log.info("{}, New lessonRequests: {}", num,
						lessonRequests.stream().map(LessonRequest::lessonName)
								.collect(Collectors.joining(",")));
				Collection<ApprovedLesson> approvedLessons = school.process(lessonRequests);
				log.info("{}, Ready approvedLessons: {}", num, approvedLessons.stream()
						.map(ApprovedLesson::approvedLessonName)
						.collect(Collectors.joining(",")));
			});
		}
	}

	private static LessonRequest generateLessonRequest() {
		return new LessonRequest(LESSONS[RandomUtils.nextInt(0, LESSONS.length)],
				new Date(ThreadLocalRandom
				.current()
				.nextLong(currentTimeMillis(), currentTimeMillis() + 10000000)),
				STUDENTS[RandomUtils.nextInt(0,STUDENTS.length)],
				TUTORS[RandomUtils.nextInt(0, TUTORS.length)]);
	}

	private static Collection<LessonRequest> generateLessonRequests() {
		List<LessonRequest> lessonRequests = new ArrayList<>();
		for (int i = 0; i < RandomUtils.nextInt(1, 5); ++i) {
			lessonRequests.add(generateLessonRequest());
		}
		return lessonRequests;
	}

	private void delay() {
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

	}
}
