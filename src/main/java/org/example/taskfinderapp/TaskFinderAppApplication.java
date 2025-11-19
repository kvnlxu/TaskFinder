package org.example.taskfinderapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration.class)
public class TaskFinderAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskFinderAppApplication.class, args);
	}

}
