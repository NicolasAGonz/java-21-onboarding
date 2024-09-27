package com.dev.java.MSCuentas.utils;

import java.util.Random;

public class RandomNumberGenerator {

    public static String generateRandomNumber() {
        String fixedPart = "1";
        Random random = new Random();

        StringBuilder randomPart = new StringBuilder();

        // Generar los 9 dígitos restantes aleatoriamente
        for (int i = 0; i < 9; i++) {
            int digit = random.nextInt(10);
            randomPart.append(digit);
        }

        return fixedPart + randomPart;
    }
}
