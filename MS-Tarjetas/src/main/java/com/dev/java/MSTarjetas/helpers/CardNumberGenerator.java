package com.dev.java.MSTarjetas.helpers;

import java.util.Random;

public class CardNumberGenerator {

    // Método para generar un String aleatorio de 16 dígitos, con los 4 primeros fijos
    public static String generateCardNumber() {
        String fixedPart = "4235"; // Los primeros 4 dígitos fijos
        Random random = new Random();

        StringBuilder randomPart = new StringBuilder();

        // Generar los 12 dígitos restantes aleatoriamente
        for (int i = 0; i < 12; i++) {
            int digit = random.nextInt(10);
            randomPart.append(digit);
        }

        return fixedPart + randomPart;
    }

}
