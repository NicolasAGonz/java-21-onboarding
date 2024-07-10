package com.dev.java.MSCuentas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class MsCuentasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsCuentasApplication.class, args);
	}

}
