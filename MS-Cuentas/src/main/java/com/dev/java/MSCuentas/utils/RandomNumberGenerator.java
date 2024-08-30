package com.dev.java.MSCuentas.utils;

import java.util.Random;

public class RandomNumberGenerator {

    public static String generateRandomNumber(int length) {
        String digits = "1234567890";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(digits.length());
            sb.append(digits.charAt(index));
        }

        return sb.toString();
    }
}
