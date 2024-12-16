package com.example.demo;

import com.example.demo.config.AppConfig;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Getter
@Setter
@SpringBootApplication
public class DemoApplication{

	private final AppConfig appConfig;

	@Autowired
	public DemoApplication(AppConfig appConfig) {
		this.appConfig = appConfig;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}