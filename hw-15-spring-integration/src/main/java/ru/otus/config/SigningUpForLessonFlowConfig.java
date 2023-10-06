package ru.otus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.MessageChannelSpec;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.PollerSpec;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import ru.otus.service.LessonService;

@Configuration
public class SigningUpForLessonFlowConfig {

    //requestChannel
    @Bean
    public MessageChannelSpec<?, ?> inputChannel() {
        return MessageChannels.queue(10);
    }

    //lessonChannel
    @Bean
    public MessageChannelSpec<?, ?> outputChannel() {
        return MessageChannels.publishSubscribe();
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerSpec poller() {
        return Pollers.fixedRate(100).maxMessagesPerPoll(2);
    }

    @Bean
    public IntegrationFlow onlineSchoolFlow(LessonService lessonService) {
        return IntegrationFlow.from(inputChannel())
                .split()
                .handle(lessonService, "approve")
                .aggregate()
                .channel(outputChannel())
                .get();
    }
}
