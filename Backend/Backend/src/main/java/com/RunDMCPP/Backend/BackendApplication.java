package com.RunDMCPP.Backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// BackendApplication class. This class is the main class for the backend.
@SpringBootApplication
public class BackendApplication {
	// Main method, entry point of Spring app
	// Sets up app context, loads all components, starts server
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}
