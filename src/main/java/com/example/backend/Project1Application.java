package com.example.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the Spring Boot application.
 * This class is responsible for bootstrapping the Spring Boot application.
 * The @SpringBootApplication annotation enables Spring Boot's auto-configuration, component scanning, and property support.
 * The application starts by running the main method which calls SpringApplication.run().
 */
@SpringBootApplication
public class Project1Application {

	/**
	 * The main method that starts the Spring Boot application.
	 *
	 * @param args Command-line arguments passed during the application startup.
	 */
	public static void main(String[] args) {
		SpringApplication.run(Project1Application.class, args);
	}

}
