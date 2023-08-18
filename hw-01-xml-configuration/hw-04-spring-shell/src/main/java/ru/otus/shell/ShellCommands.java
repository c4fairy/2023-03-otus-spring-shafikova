package ru.otus.shell;

import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.otus.service.ExamService;

@ShellComponent
public class ShellCommands {

    private final ExamService examService;

    private boolean isGreet = false;

    private boolean isStart = false;

    public ShellCommands(ExamService examService) {
        this.examService = examService;
    }

    @ShellMethod(value = "Greeting", key = {"greeting", "grt", "g"})
    @ShellMethodAvailability(value = "isNotGreet")
    public void greeting() {
        examService.getGreetingName();
        isGreet = true;
    }

    @ShellMethod(value = "Start exam", key = {"start", "s"})
    @ShellMethodAvailability(value = "isGreet")
    public void startExam() throws Exception {
        examService.startExam();
        isStart = true;
    }

    @ShellMethod(value = "Print result", key = {"print", "p"})
    @ShellMethodAvailability(value = "isStart")
    public void printExamResult() {
        examService.printExamResult();
    }

    private Availability isNotGreet() {
        return isGreet ? Availability.unavailable("You already enter your name") : Availability.available();
    }

    private Availability isGreet() {
        return !isGreet ? Availability.unavailable("Enter your name please") : Availability.available();
    }

    private Availability isStart() {
        return !(isGreet && isStart) ?
                Availability.unavailable("Enter your name and start exam") :
                Availability.available();
    }
}
