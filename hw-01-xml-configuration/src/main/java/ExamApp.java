import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.service.ExamPrinterImpl;
import ru.otus.service.TaskServiceImpl;

import java.io.IOException;
import java.net.URISyntaxException;

public class ExamApp {
    public static void main(String[] args) throws IOException, URISyntaxException {
        // класс контекста
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");

        TaskServiceImpl taskService = context.getBean("taskService", TaskServiceImpl.class);
        ExamPrinterImpl examPrinter = context.getBean(ExamPrinterImpl.class);
        examPrinter.printAll(taskService.getAllTasks());
        context.close();
    }
}
