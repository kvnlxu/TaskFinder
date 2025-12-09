package org.example.taskfinderapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tech.ailef.snapadmin.external.SnapAdminAutoConfiguration;

@SpringBootApplication(exclude = org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration.class)
@ImportAutoConfiguration(SnapAdminAutoConfiguration.class)
public class TaskFinderAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskFinderAppApplication.class, args);
	}

}
