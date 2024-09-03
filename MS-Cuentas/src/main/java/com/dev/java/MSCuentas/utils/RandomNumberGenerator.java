package com.dev.java.MSCuentas.utils;

import java.util.Random;

public class RandomNumberGenerator {

    private static final int MAX_INTEGER_VALUE = Integer.MAX_VALUE;

    public static String generateRandomNumber() {
        Random random = new Random();
        // Generar un número aleatorio entre 1 y el valor máximo de Integer
        int randomNumber = random.nextInt(MAX_INTEGER_VALUE) + 1;
        return String.valueOf(randomNumber);
    }
}
