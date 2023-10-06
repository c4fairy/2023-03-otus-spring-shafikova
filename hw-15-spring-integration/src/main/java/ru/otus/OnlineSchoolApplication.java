package ru.otus;

import lombok.val;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import ru.otus.service.SigningUpService;

@SpringBootApplication
@EnableIntegration
@IntegrationComponentScan
public class OnlineSchoolApplication {

    public static void main(String[] args) {
        val ctx = SpringApplication.run(OnlineSchoolApplication.class, args);
        val signingUpService = ctx.getBean(SigningUpService.class);
        signingUpService.startGenerateLessonsLoop();
    }
}
