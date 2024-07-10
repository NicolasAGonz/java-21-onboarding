package com.dev.java.notification_service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewUserCreatedEvent {
    private String newUser; //TODO: wrap required user data
}
