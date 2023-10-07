package ru.otus.actuators;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Log4j2
@Component
@RequiredArgsConstructor
public class DbHealthIndicator implements HealthIndicator {
    private static final String MESSAGE = "message";
    private final DataSource dataSource;

    @Override
    public Health health() {
        try {
            Connection connection =  dataSource.getConnection();
            if (isConnectionValid(connection)) {
                return Health.up()
                        .status(Status.UP)
                        .withDetail(MESSAGE, "Database is working")
                        .build();
            }
            else
                return Health.down()
                        .status(Status.DOWN)
                        .withDetail(MESSAGE, "Database is not valid")
                        .build();
        } catch (Exception e) {
            return Health.down(e)
                    .status(Status.DOWN)
                    .withDetail(MESSAGE, "Database is down")
                    .build();
        }
    }

    public static boolean isConnectionValid(Connection connection)
    {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.prepareStatement("SELECT 1");
                log.info("Connection is valid");
                return true;
            }
        }
        catch (SQLException e) {
            log.info("Connection problems : " + e.getMessage());
        }
        return false;
    }
}
