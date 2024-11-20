package com.thws.eventmanager;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EventmanagerApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();
		System.setProperty("STRIPE_API_KEY_TEST", dotenv.get("STRIPE_API_KEY_TEST"));

		SpringApplication.run(EventmanagerApplication.class, args);
	}

}