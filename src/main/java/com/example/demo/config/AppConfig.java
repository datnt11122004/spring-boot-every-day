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
    private static final Logger LOGGER = Logger.getLogger(AppConfig.class.getName());

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
    private String APP_JWT_SECRET_KEY;
    private String APP_JWT_EXPIRATION;
    private String APP_JWT_EXPIRATION_REFRESH_TOKEN;

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

    @PostConstruct
    public void init() {
        dotenv.entries().forEach(entry -> {
            System.setProperty(entry.getKey(), entry.getValue());
        });

        APP_JWT_EXPIRATION = dotenv.get("APP_JWT_EXPIRATION");
        APP_JWT_EXPIRATION_REFRESH_TOKEN = dotenv.get("APP_JWT_EXPIRATION_REFRESH_TOKEN");

        validateEnvVariables();
    }

    private void validateEnvVariables() {
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.getType().equals(String.class)) {
                try {
                    field.setAccessible(true);
                    LOGGER.info("Validating " + field.getName() + field.get(this));
                    String value = (String) field.get(this);
                    if (value == null || value.isEmpty()) {
                        LOGGER.severe(field.getName() + " is not set");
                        throw new IllegalStateException(field.getName() + " is not set");
                    }
                } catch (IllegalAccessException e) {
                    LOGGER.severe("Unable to access field: " + field.getName());
                    throw new RuntimeException(e);
                }
            }
        }

        // Validate JWT_EXPIRATION and APP_JWT_EXPIRATION_REFRESH_TOKEN as numbers
        try {
            Integer.parseInt(APP_JWT_EXPIRATION);
        } catch (NumberFormatException e) {
            LOGGER.severe("JWT_EXPIRATION is not a valid number");
            throw new IllegalStateException("JWT_EXPIRATION is not a valid number");
        }

        try {
            Integer.parseInt(APP_JWT_EXPIRATION_REFRESH_TOKEN);
        } catch (NumberFormatException e) {
            LOGGER.severe("APP_JWT_EXPIRATION_REFRESH_TOKEN is not a valid number");
            throw new IllegalStateException("APP_JWT_EXPIRATION_REFRESH_TOKEN is not a valid number");
        }
    }
}