package ru.otus;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@EnableMongock
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MongoRepositoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongoRepositoryApplication.class, args);
    }

}
