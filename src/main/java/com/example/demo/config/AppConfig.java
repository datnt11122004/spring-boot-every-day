package com.example.demo.config;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.Getter;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.util.logging.Logger;

@Getter
@Configuration
public class AppConfig {

    private final Dotenv dotenv = Dotenv.load();

    // Environment variables

    //App environment variables
    private String APP_ENV;

    //App Details
    private String APP_NAME;
    private String APP_VERSION;
    private String APP_FRONTEND_URL;

    //Configurations for the launch app
    private String APP_PORT;
    private String APP_HOST;
    private String APP_BASE_URL;

    //JWT Token Configurations
    private String JWT_SECRET_KEY;
    private String JWT_EXPIRATION;
    private String JWT_EXPIRATION_REFRESH_TOKEN;

    // Email Configurations
    private String APP_MAIL_HOST;
    private String APP_MAIL_PORT;
    private String APP_MAIL_USERNAME;
    private String APP_MAIL_PASSWORD;
    private String APP_MAIL_SMTP_AUTH;
    private String APP_MAIL_SMTP_TLS_ENABLE;

    // Database Configurations
    private String APP_DB_URL;
    private String APP_DB_USERNAME;
    private String APP_DB_PASSWORD;

    // Redis Configurations
    private String APP_REDIS_HOST;
    private String APP_REDIS_PORT;

    // Kafka Configurations
    private String APP_KAFKA_SERVER;
    private String APP_KAFKA_BROKER_PORT;

    // Logger Configurations
    private String APP_LOG_LEVEL;
    private String APP_LOG_FILE_PATH;
    private String APP_LOG_FILE_NAME;
    private String APP_LOG_FILE_MAX_SIZE;
    private String APP_LOG_FILE_MAX_HISTORY;
    private String APP_LOG_FILE_MAX_AGE;
    private String APP_LOG_CONSOLE_ENABLE;
    private String APP_LOG_CONSOLE_LEVEL;

    @PostConstruct
    public void init() {
        for (Field field : this.getClass().getDeclaredFields()) {
            if (field.getType().equals(String.class)) {
                try {
                    String value = getDotenv().get(field.getName());
                    if (value == null || value.isEmpty()) {
                        Logger.getLogger(AppConfig.class.getName()).severe(field.getName() + " must not be null or empty");
                        throw new IllegalArgumentException(field.getName() + " must not be null or empty");
                    }
                    field.set(this, value);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}