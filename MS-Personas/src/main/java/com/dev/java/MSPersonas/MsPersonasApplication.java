package com.dev.java.MSPersonas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class MsPersonasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsPersonasApplication.class, args);
	}

}
