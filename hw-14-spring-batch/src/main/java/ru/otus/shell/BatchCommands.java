package ru.otus.shell;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@RequiredArgsConstructor
@ShellComponent
public class BatchCommands {

    private final JobLauncher jobLauncher;

    private final Job migrateMongoToH2;

    @SneakyThrows
    @ShellMethod(value = "migrateMongoToH2", key = "s")
    public void startMigrationFromMongoToMySql() {
        jobLauncher.run(migrateMongoToH2, new JobParameters());
    }

}
