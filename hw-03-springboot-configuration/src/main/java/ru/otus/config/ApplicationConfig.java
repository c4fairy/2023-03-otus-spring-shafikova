package ru.otus.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Locale;

@ConfigurationProperties(prefix = "application")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationConfig {
    private String fileName;

    private String locale;

    private Integer passScore;

}