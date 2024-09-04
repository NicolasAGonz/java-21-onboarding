package com.dev.java.MSCuentas.utils;

import java.util.Random;

public class RandomNumberGenerator {

    private static final int MAX_INTEGER_VALUE = Integer.MAX_VALUE;

    public static String generateRandomNumber() {
        Random random = new Random();
        int firstDigit = random.nextInt(9) + 1;
        int remainingDigits = random.nextInt(1_000_000_000); // Hasta 9 dígitos
        String randomNumber = firstDigit + String.format("%09d", remainingDigits);
        return randomNumber;
    }
}
