package service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.dao.TaskDao;
import ru.otus.domain.Task;
import ru.otus.service.TaskServiceImpl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class TaskServiceImplTest {

    @Mock
    private TaskDao dao;

    private TaskServiceImpl taskService;

    @BeforeEach
    void setUp() {
        taskService = new TaskServiceImpl(dao);
    }

    @Test
    void shouldReturnExactlyAmountOfTasks() throws IOException, URISyntaxException {
        given(dao.getAllTasks()).willReturn(Arrays.asList(
                new Task("question", "answer 1", Arrays.asList("answer 1", "answer 2", "answer 3")),
                new Task("question", "answer 2", Arrays.asList("answer 1", "answer 2", "answer 3")),
                new Task("question", "answer 3", Arrays.asList("answer 1", "answer 2", "answer 3"))
        ));
        assertThat(taskService.getAllTasks().size()).isEqualTo(3);
        verify(dao).getAllTasks();
    }

}