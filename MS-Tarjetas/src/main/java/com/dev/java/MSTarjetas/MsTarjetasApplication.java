package com.dev.java.MSTarjetas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class MsTarjetasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsTarjetasApplication.class, args);
	}

}
