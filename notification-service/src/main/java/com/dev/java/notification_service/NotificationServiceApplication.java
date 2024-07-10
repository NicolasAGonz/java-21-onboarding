package com.dev.java.notification_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.kafka.annotation.KafkaListener;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class NotificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationServiceApplication.class, args);
	}

	@KafkaListener(topics= "newUserCreatedTopic")
	public void handleNotification(NewUserCreatedEvent newUserCreatedEvent){

	}

}
